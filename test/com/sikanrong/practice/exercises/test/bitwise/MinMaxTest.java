package com.sikanrong.practice.exercises.test.bitwise;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

import com.sikanrong.practice.exercises.bitwise.MinMax;

public class MinMaxTest {
	@Test
	void testMin() {
		assertEquals(1, MinMax.min(1,2));
		assertEquals(1, MinMax.min(2,1));
		assertEquals(1, MinMax.min(1,9));
		assertEquals(2, MinMax.min(3,2));
		assertEquals(7, MinMax.min(8,7));
	}
	
	void testMax() {
		assertEquals(2, MinMax.max(1,2));
		assertEquals(2, MinMax.max(2,1));
		assertEquals(9, MinMax.max(1,9));
		assertEquals(3, MinMax.max(3,2));
		assertEquals(8, MinMax.max(8,7));
	}
}
