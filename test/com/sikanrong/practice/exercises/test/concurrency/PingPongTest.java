package com.sikanrong.practice.exercises.test.concurrency;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.concurrency.PingPongAlternate;
import java.util.concurrent.atomic.*;

public class PingPongTest {
	@Test
	void testAlternatingGame() {
		PingPongAlternate.startGame();
		AtomicBoolean lastPing = new AtomicBoolean();
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		PingPongAlternate.stopGame();
		
		PingPongAlternate.results.stream()
			.peek(f -> {
				//set as though it were the result at index -1
				lastPing.set(f != "ping");
			})
			.forEach(s -> {
				//Check that each element in the results collection 
				//alternates perfectly between ping and pong
				assertEquals(!lastPing.get(), s == "ping");
				lastPing.set(s == "ping");
			});
		
	}
}
