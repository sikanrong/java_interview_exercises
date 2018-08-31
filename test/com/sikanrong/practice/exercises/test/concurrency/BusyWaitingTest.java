package com.sikanrong.practice.exercises.test.concurrency;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.concurrency.BusyWaiting;

import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class BusyWaitingTest {
	@Test
	void testCPUBusyWaiting(){
		final ThreadMXBean tmb = ManagementFactory.getThreadMXBean();

		long executionTime = 3000;
		BusyWaiting.getBusy(executionTime);
		long time = TimeUnit.NANOSECONDS.toMillis(tmb.getCurrentThreadUserTime());
		assertEquals(true, (time < executionTime));
	}
}
