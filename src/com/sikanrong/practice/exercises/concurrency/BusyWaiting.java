package com.sikanrong.practice.exercises.concurrency;


public class BusyWaiting {	
	public static class SleepyThread extends Thread{
		private Object lock;
		private long sleeptime;
		
		public SleepyThread(Object lock, long sleeptime) {
			this.lock = lock;
			this.sleeptime = sleeptime;
		}
		
		public void run () {
			synchronized(lock){
				try {
					Thread.sleep(sleeptime);
					lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void getBusy(long sleepTime) {
		Object lock = new Object();
		SleepyThread st = new BusyWaiting.SleepyThread(lock, sleepTime);
		
		//Spin That Lock!!
		//st.start();
		//while(st.isAlive()){continue;} 
		
		synchronized(lock) {
			st.start();
			try {
				lock.wait();
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
}
