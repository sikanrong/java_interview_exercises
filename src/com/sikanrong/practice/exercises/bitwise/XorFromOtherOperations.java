package com.sikanrong.practice.exercises.bitwise;

public class XorFromOtherOperations {
	public static int xor(int a, int b) {
		int out = ((~a | b) & (a | ~b));
		
		if(((a*b) >>> 31) != (out >>> 31)) {
			out = ~out;
		};
	
		return out;
	}
}
