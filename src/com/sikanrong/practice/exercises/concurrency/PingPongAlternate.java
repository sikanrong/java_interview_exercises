package com.sikanrong.practice.exercises.concurrency;

import java.util.List;
import java.util.ArrayList;

public class PingPongAlternate {
	
	public static List<String> results = new ArrayList<>();
	
	private static class Mutex{
		public boolean isPing = false;
		public boolean terminate = false;
	}

	private static class PingPong{
		
		private static final Mutex lock = new Mutex();
		
		public void ping() {
			synchronized(lock) {
				results.add("ping");
				
				lock.isPing = true;
				lock.notifyAll();
				
				try {
					lock.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void pong() {
			synchronized(lock) {
				
				if(!lock.isPing) {
					try {
						lock.wait();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				results.add("pong");
				lock.isPing = false;
				
				lock.notifyAll();
			}
		}
	}
	
	public static void stopGame() {
		PingPong.lock.terminate = true;
	}
	
	public static void startGame() {
		PingPong pp = new PingPong();
		
		Thread pingThread = new Thread() {
			public void run() {
				while(!PingPong.lock.terminate) {
					pp.ping();
				}
			}
		};
		
		Thread pongThread = new Thread() {
			public void run() {
				while(!PingPong.lock.terminate) {
					pp.pong();
				}
			}
		};
		
		pongThread.start();
		pingThread.start();
	}
	
}
