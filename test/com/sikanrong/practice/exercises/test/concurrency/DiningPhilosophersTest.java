package com.sikanrong.practice.exercises.test.concurrency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.sikanrong.practice.exercises.concurrency.DiningPhilosophers;

public class DiningPhilosophersTest {
	@Test
	void testDiningPhilosophersStarvation(){
		int diners = 5;
		DiningPhilosophers dp = new DiningPhilosophers(diners);
		dp.startDining();
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < diners; i++) {
			long thisDinerEaten = dp.philosophers[i].eaten;
			assertFalse(thisDinerEaten == 0, i+"th philospher has eaten?");
		}
		
	}
}
