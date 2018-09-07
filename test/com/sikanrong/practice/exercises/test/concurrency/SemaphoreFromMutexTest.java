package com.sikanrong.practice.exercises.test.concurrency;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.concurrency.SemaphoreFromMutex;

@SuppressWarnings("unused")
public class SemaphoreFromMutexTest {
	
	private static class PublicBathroomUser extends Thread {
		
		private static class StallOccupiedException extends Exception{
			private static final long serialVersionUID = 3113700001L;
		}
		
		public static boolean[] stalls = new boolean[2];
		public static SemaphoreFromMutex accessControl = new SemaphoreFromMutex(2);
		private boolean safeTerinate;
		private static int nextId = 0;
		
		public int id;
		public int myStall;
		
		public PublicBathroomUser() {
			this.id = nextId++;
			this.safeTerinate = false;
		}
		
		public void terminateSafely() {
			this.safeTerinate = true;
		}
		
		
		public void run() {
			
			//let's start peeing!!
			while(!this.safeTerinate) {
				accessControl.acquire();
				
				try {
					this.enterStall();
					this.exitStall();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
				accessControl.release();
			}
		}
		
		public synchronized void enterStall() throws InterruptedException, StallOccupiedException {
			for(int i = 0; i < stalls.length; i++) {
				if(!stalls[i]) {
					myStall = i;
					stalls[i] = true;
					Thread.sleep(1);
					return;
				}
			}
			
			//should never be reached
			throw new StallOccupiedException();
		}
		
		public synchronized void exitStall() {
			stalls[this.myStall] = false;
		}
		
	}
	
	@Test
	void testSemaphoreFromMutex(){
		ArrayList<PublicBathroomUser> threads = new ArrayList<>(4);
		
		PublicBathroomUser a = new PublicBathroomUser();
		PublicBathroomUser b = new PublicBathroomUser();
		PublicBathroomUser c = new PublicBathroomUser();
		PublicBathroomUser d = new PublicBathroomUser();
		
		threads.add(a);
		threads.add(b);
		threads.add(c);
		threads.add(d);
		
		threads.forEach(t -> {
			t.start();
		});
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threads.forEach(t -> {
			assertEquals(true, t.isAlive());
			t.terminateSafely();
		});

	}
}
