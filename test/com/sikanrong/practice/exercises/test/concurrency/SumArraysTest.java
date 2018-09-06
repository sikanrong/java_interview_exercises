package com.sikanrong.practice.exercises.test.concurrency;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.concurrency.SumArrays;

public class SumArraysTest {
	@Test
	void testSumArrays() {
		int k = 1000000;
		int a[] = new int[k];
		int b[] = new int[k];
		int expected[] = new int[k];
		for(int i = 0; i < k; i++) {
			a[i] = i;
			b[i] = k - i;
			expected[i] = a[i] + b[i];
		}
		
		int actual[] = SumArrays.sumArrays(a, b, 25000);
		
		assertArrayEquals(expected, actual);
	}
}
