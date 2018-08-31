package com.sikanrong.practice.exercises.concurrency;

public class SleepyThread extends Thread{
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
