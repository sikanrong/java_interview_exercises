package com.sikanrong.practice.exercises.test.concurrency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import com.sikanrong.practice.exercises.concurrency.ProducerConsumer;

public class ProducerConsumerTest {
	@Test
	void testProducerConsumerDeaclock() {
		ProducerConsumer.IntBuffer ib = ProducerConsumer.getBusy();
		int lastBuffer[] = new int[8]; 
		for(int i = 0; i < 2000; i++) {
			try{
				Thread.sleep(1);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			assertFalse(Arrays.equals(ib.buffer, lastBuffer));
			System.arraycopy(ib.buffer, 0, lastBuffer, 0, 8);
		}
	}
}
