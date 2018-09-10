package com.sikanrong.practice.exercises.bitwise;

public class XorFromOtherOperations {
	public static int xor(int a, int b) {
		int out = ((a | b) & (~a | ~b));
	
		return out;
	}
}
