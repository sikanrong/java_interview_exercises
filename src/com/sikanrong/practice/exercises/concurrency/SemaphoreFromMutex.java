package com.sikanrong.practice.exercises.concurrency;

public class SemaphoreFromMutex {
	private int totalPermits;
	private int permitsIssued = 0;
	public SemaphoreFromMutex(int permits) {
		this.totalPermits = permits;
	}
	
	public synchronized void acquire() {
		while(permitsIssued >= totalPermits) {
			try {
				this.wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		permitsIssued++;
	}
	
	public synchronized void release() {
		permitsIssued--;
		this.notify(); //Notify one waiting thread...
	}
}
