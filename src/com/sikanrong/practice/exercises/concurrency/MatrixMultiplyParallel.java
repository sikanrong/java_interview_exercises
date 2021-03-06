//Alex Pilafian 2018
//Totally original implementation based on pseudo-code presented in CLRS p.794

package com.sikanrong.practice.exercises.concurrency;

import java.util.stream.IntStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MatrixMultiplyParallel {
	private static ExecutorService pool;
	
	public MatrixMultiplyParallel() {
		pool = Executors.newCachedThreadPool();
	}

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
			
			public boolean hasZeroDims() {
				return Math.min(dims[0], dims[1]) == 0;
			}

			public boolean isSingleElement() {
				return (int) Math.max(dims[0], dims[1]) == 1;
			}
		}

		private Object data[][];
		private int[] dims = new int[2];
		private Object defaultValue;

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
			Object toGet = data[y][x];
			return (T) ((toGet == null)? this.defaultValue : toGet);
		}

		public void set(T t, int x, int y) {
			data[y][x] = t;
		}
		
		public void setDefault(T t) {
			this.defaultValue = t;
		}

		public int[] getDims() {
			return dims;
		}

		public Partition fpart(int x, int y, Partition p) {
			return this.partition(x, y, p, true);
		}
		
		public Partition cpart(int x, int y, Partition p) {
			return this.partition(x, y, p, false);
		}
		
		public Partition partition(int x, int y, Partition p, boolean floorwise) {
			// floorwise partition is equal to ceilwise partition WLOG for all
			// matrices of odd dimensionality.

			int hf = (int) Math.floor((float)p.dims[0] / 2f);
			int hc = (int) Math.ceil((float)p.dims[0] / 2f);
			int vf = (int) Math.floor((float)p.dims[1] / 2f);
			int vc = (int) Math.ceil((float)p.dims[1] / 2f);

			int viewX;
			int viewY;
			int dimsX;
			int dimsY;
			
			if(floorwise) {
				viewX = p.view[0] + (x * hf);
				viewY = p.view[1] + (y * vf);
				dimsX = (x == 0)? hf : hc;
				dimsY = (y == 0)? vf : vc;
			}else {
				viewX = p.view[0] + (x * hc);
				viewY = p.view[1] + (y * vc);
				dimsX = (x == 0)? hc : hf;
				dimsY = (y == 0)? vc : vf;
			}
			
			//Avoids double-counting when we end up with a [1x1] * [2x1]
			//Type of situation.
			if(p.dims[0] == 1 && p.dims[1] == 1) {
				dimsX = 1;
				dimsY = 1;
			}
			
			return new Partition(viewX, viewY, dimsX, dimsY);
		}

		public Partition fullPartition() {
			return new Partition(0, 0, dims[0], dims[1]);
		}
		
		
		@SuppressWarnings("unchecked")
		public T[][] toArray(){
			return (T[][])data;
		}

	}
	
	private static class MultiplyRecursiveStep implements Runnable{
		private Matrix<Integer> c; 
		private Matrix<Integer> a;
		private Matrix<Integer> b;
		private Matrix.Partition pc; 
		private Matrix.Partition pa;
		private Matrix.Partition pb;
		
		public MultiplyRecursiveStep(
			Matrix<Integer> c, 
			Matrix<Integer> a, 
			Matrix<Integer> b,
			Matrix.Partition pc, 
			Matrix.Partition pa, 
			Matrix.Partition pb
		) {
			this.c = c;
			this.a = a;
			this.b = b;
			this.pc = pc;
			this.pa = pa;
			this.pb = pb;
		}
		
		@SuppressWarnings("rawtypes")
		public void run() {
			if( pc.hasZeroDims() || 
				pa.hasZeroDims() || 
				pb.hasZeroDims() ) {
				return;
			} else if (
				pc.isSingleElement() && 
				pa.isSingleElement() && 
				pb.isSingleElement()
			) {
				int _a = a.get(pa.view[0], pa.view[1]);
				int _b = b.get(pb.view[0], pb.view[1]);
				int p = _a * _b;
				c.set(p, pc.view[0], pc.view[1]);
			} else {
				Matrix<Integer> t = new Matrix<>(c.getDims());
				
				Future[] _f = new Future[8];

				_f[0] = pool.submit(new MultiplyRecursiveStep(c, a, b, c.fpart(0, 0, pc), a.fpart(0, 0, pa), b.fpart(0, 0, pb)));
				_f[1] = pool.submit(new MultiplyRecursiveStep(t, a, b, t.fpart(0, 0, pc), a.fpart(1, 0, pa), b.fpart(0, 1, pb)));
				
				_f[2] = pool.submit(new MultiplyRecursiveStep(c, a, b, c.fpart(1, 0, pc), a.fpart(0, 0, pa), b.fpart(1, 0, pb)));
				_f[3] = pool.submit(new MultiplyRecursiveStep(t, a, b, t.fpart(1, 0, pc), a.fpart(1, 0, pa), b.fpart(1, 1, pb)));
				
				_f[4] = pool.submit(new MultiplyRecursiveStep(c, a, b, c.fpart(0, 1, pc), a.fpart(0, 1, pa), b.fpart(0, 0, pb)));
				_f[5] = pool.submit(new MultiplyRecursiveStep(t, a, b, t.fpart(0, 1, pc), a.fpart(1, 1, pa), b.fpart(0, 1, pb)));
				
				_f[6] = pool.submit(new MultiplyRecursiveStep(c, a, b, c.fpart(1, 1, pc), a.fpart(0, 1, pa), b.fpart(1, 0, pb)));
				_f[7] = pool.submit(new MultiplyRecursiveStep(t, a, b, t.fpart(1, 1, pc), a.fpart(1, 1, pa), b.fpart(1, 1, pb)));
				
				try {
					for(int i = 0; i < 8; i++) {
						_f[i].get();
					}
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				IntStream.range(pc.view[0], (pc.view[0] + pc.dims[0])).parallel().forEach(i -> {
					IntStream.range(pc.view[1], (pc.view[1] + pc.dims[1])).parallel().forEach(j -> {
						int _c = c.get(i, j);
						int _t = t.get(i, j);
						int s = _c + _t;
						c.set(s, i, j);
					});
				});
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Matrix<Integer> multiply(Matrix<Integer> a, Matrix<Integer> b) {		
		Matrix<Integer> c = new Matrix<>(b.getDims()[0], a.getDims()[1]);
		
		a.setDefault(0);
		b.setDefault(0);
		c.setDefault(0);
		
		Future f = pool.submit(new MultiplyRecursiveStep(c,a,b, c.fullPartition(), a.fullPartition(), b.fullPartition()));
		
		try {
			f.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}
