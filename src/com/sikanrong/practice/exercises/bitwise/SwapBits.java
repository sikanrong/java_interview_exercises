package com.sikanrong.practice.exercises.bitwise;

public class SwapBits {
	public static byte swapBits(byte x, int p1, int p2, int n) {
		
		byte set1 = (byte)((x >> p1) & ((0b1 << n) - 1));
		byte set2 = (byte)((x >> p2) & ((0b1 << n) - 1));
		
		byte y = (byte)(set1 ^ set2);
		y = (byte)((y << p1) | (y << p2));
		return (byte) (x ^ y);
	}
}
