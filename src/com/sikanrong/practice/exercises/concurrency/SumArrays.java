//CLRS Exercise 27P-1 

package com.sikanrong.practice.exercises.concurrency;

/* T_1 = O(n); n = MAX(a.length, b.length); 
 * T_Infinity = O(C); C = grainSize;
 * parallelism = T_1/T_Infinity = (MAX(a.length, b.length) / grainSize);
 * 
 * Most efficient grainSize such that parallelism = P where P is number of 
 * cores in the machine on which this code is run. if parallelism > P then 
 * we stop gaining efficiency and start losing it to scheduling overhead. 
 * */

public class SumArrays {
	public static int[] sumArrays(int[] a, int[] b, int grainSize) {
		int largerLength = (int)Math.max(a.length, b.length);
		int c[] = new int[largerLength];
		Thread t[] = new Thread[(int)(largerLength / grainSize)]; 
		
		for(int i = 0; i < largerLength; i += grainSize) {
			final int _i = i;
			int ti = (int)(i / grainSize);
			t[ti] = new Thread() {
				public void run() {
					for(int j = 0; j < grainSize; j++) {
						int _j = _i + j;
						c[_j] = a[_j] + b[_j];
					}
				}
			};
			t[ti].start();
		}
		
		for(Thread _t : t) {
			try {
				_t.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}
}
