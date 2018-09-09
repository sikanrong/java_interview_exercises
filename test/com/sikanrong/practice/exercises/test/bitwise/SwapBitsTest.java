package com.sikanrong.practice.exercises.test.bitwise;

import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.bitwise.SwapBits;

import static org.junit.jupiter.api.Assertions.*;

public class SwapBitsTest {
	//public static void swapBits(int x, int p1, int p2, int n)
	@Test
	public void testSwapBits() {
		assertEquals((byte)227, (byte)SwapBits.swapBits((byte)47, 1, 5, 3));
		assertEquals((byte)7, (byte)SwapBits.swapBits((byte)28, 0, 3, 2));
	}
	
}
