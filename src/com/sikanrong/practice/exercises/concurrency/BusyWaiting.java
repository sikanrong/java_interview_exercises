package com.sikanrong.practice.exercises.concurrency;

import com.sikanrong.practice.exercises.concurrency.SleepyThread;

public class BusyWaiting {	
	public static void getBusy(long sleepTime) {
		Object lock = new Object();
		SleepyThread st = new SleepyThread(lock, sleepTime);
		
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
