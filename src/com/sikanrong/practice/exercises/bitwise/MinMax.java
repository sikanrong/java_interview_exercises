package com.sikanrong.practice.exercises.bitwise;

public class MinMax {
	public static int min(int a, int b) {
		return b + ((a - b) & ((a - b) >> 31));
	}
	
	public static int max(int a, int b) {
		return a - ((a - b) & ((a - b) >> 31));
	}
}
