package com.sikanrong.practice.exercises.concurrency;

import java.util.stream.IntStream;

public class MatrixMultiplyParallel {

	public static class Matrix<T> {

		public static class Partition {
			public int[] view;
			public int[] dims;

			public Partition() {
				view = new int[2];
				dims = new int[2];
			}

			public Partition(int[] view, int[] dims) {
				this();

				IntStream.range(0, 1).forEach(i -> {
					this.view[i] = view[i];
					this.dims[i] = dims[i];
				});
			}

			public Partition(int viewx, int viewy, int dimsx, int dimsy) {
				this();

				this.view[0] = viewx;
				this.view[1] = viewy;
				this.dims[0] = dimsx;
				this.dims[1] = dimsy;
			}
		}

		private Object data[][];
		private int[] dims = new int[2];

		public Matrix(T[][] _d) {
			data = _d;
			dims[0] = _d[0].length;
			dims[1] = _d.length;
		}

		public Matrix(int[] dims) {
			this.dims = dims;
			this.data = new Object[dims[1]][dims[0]];
		}

		public Matrix(int xDims, int yDims) {
			this.dims[0] = xDims;
			this.dims[1] = yDims;

			this.data = new Object[dims[1]][dims[0]];
		}

		@SuppressWarnings("unchecked")
		public T get(int x, int y) {
			return (T) data[y][x];
		}

		public void set(T t, int x, int y) {
			data[y][x] = t;
		}

		public int[] getDims() {
			return dims;
		}

		public Partition partition(int x, int y) {
			return this.partition(x, y, this.fullPartition());
		}

		public Partition partition(int x, int y, Partition p) {
			// floorwise partition is equal to ceilwise partition WLOG for all
			// matrices of odd dimensionality.

			int hf = (int) Math.floor(p.dims[0] / 2);
			int hc = (int) Math.ceil(p.dims[0] / 2);
			int vf = (int) Math.floor(p.dims[0] / 2);
			int vc = (int) Math.ceil(p.dims[0] / 2);

			int viewX = p.view[0] + (x * hf);
			int viewY = p.view[1] + (y * vf);
			int dimsX = (x == 0) ? hf : hc;
			int dimsY = (y == 0) ? vf : vc;

			return new Partition(viewX, viewY, dimsX, dimsY);
		}

		public Partition fullPartition() {
			return new Partition(0, 0, dims[0], dims[1]);
		}

		public static boolean isSingleElement(Partition p) {
			return (int) Math.max(p.dims[0], p.dims[1]) == 1;
		}
		
		@SuppressWarnings("unchecked")
		public T[][] toArray(){
			return (T[][])data;
		}

	}

	private static void matrixMultiplyRecursive(Matrix<Integer> c, Matrix<Integer> a, Matrix<Integer> b,

			Matrix.Partition pc, Matrix.Partition pa, Matrix.Partition pb) {
		if (Matrix.isSingleElement(pc) && Matrix.isSingleElement(pa) && Matrix.isSingleElement(pb)) {
			int _a = a.get(pa.view[0], pa.view[1]);
			int _b = b.get(pb.view[0], pb.view[1]);
			int p = _a * _b;
			c.set(p, pc.view[0], pc.view[1]);
		} else {
			Matrix<Integer> t = new Matrix<>(c.getDims());

			matrixMultiplyRecursive(c, a, b, c.partition(0, 0, pc), a.partition(0, 0, pa), b.partition(0, 0, pb));
			matrixMultiplyRecursive(t, a, b, t.partition(0, 0, pc), a.partition(1, 0, pa), b.partition(0, 1, pb));
			
			matrixMultiplyRecursive(c, a, b, c.partition(1, 0, pc), a.partition(0, 0, pa), b.partition(1, 0, pb));
			matrixMultiplyRecursive(t, a, b, t.partition(1, 0, pc), a.partition(1, 0, pa), b.partition(1, 1, pb));
			
			matrixMultiplyRecursive(c, a, b, c.partition(0, 1, pc), a.partition(0, 1, pa), b.partition(0, 0, pb));
			matrixMultiplyRecursive(t, a, b, t.partition(0, 1, pc), a.partition(1, 1, pa), b.partition(0, 1, pb));
			
			matrixMultiplyRecursive(c, a, b, c.partition(1, 1, pc), a.partition(0, 1, pa), b.partition(1, 0, pb));
			matrixMultiplyRecursive(t, a, b, t.partition(1, 1, pc), a.partition(1, 1, pa), b.partition(1, 1, pb));

			IntStream.range(pc.view[0], pc.view[0] + pc.dims[0]).forEach(i -> {
				IntStream.range(pc.view[1], pc.view[1] + pc.dims[1]).forEach(j -> {
					int _c = c.get(i, j);
					int _t = t.get(i, j);
					int s = _c + _t;
					c.set(s, i, j);
				});
			});
		}
	}

	public static Matrix<Integer> multiply(Matrix<Integer> a, Matrix<Integer> b) {
		Matrix<Integer> c = new Matrix<>(a.getDims());
		matrixMultiplyRecursive(c, a, b, c.fullPartition(), a.fullPartition(), b.fullPartition());
		return c;
	}
}
