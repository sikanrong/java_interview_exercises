package com.sikanrong.practice.exercises.concurrency;

public class ProducerConsumer {
	public static class Producer extends Thread {
		IntBuffer buf;
		public Producer(IntBuffer buf) {
			this.buf = buf;
		}
		
		public void run() {
			while(true) {
				int r = (int)(Math.random()*10); 
				buf.add(r);
			}
		}
	}
	
	public static class Consumer extends Thread {
		IntBuffer buf;
		public Consumer(IntBuffer buf) {
			this.buf = buf;
		}
		public void run() {
			while(true) {
				buf.remove();
			}
		}
	}
	
	public static class IntBuffer{
		public int buffer[];
		private int nextIndex = 0;
		public IntBuffer(int length){
			buffer = new int[length];
		}
		
		public synchronized void add(int a) {
			while(nextIndex >= buffer.length) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			buffer[nextIndex++] = a;
			this.notifyAll();
		}
		
		public synchronized int remove() {
			while(nextIndex == 0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			int ret = buffer[--nextIndex];
			this.notifyAll();
			return ret;
		}
	}
	
	public static IntBuffer getBusy() {
		IntBuffer sharedBuffer = new IntBuffer(8);
		Producer p = new Producer(sharedBuffer);
		Consumer c = new Consumer(sharedBuffer);
		p.start();
		c.start();
		return sharedBuffer;
	}
}
