package com.sikanrong.practice.exercises.bitwise;

public class SmallestOfThree {
	//This works by using the twos complement bit which arises if (y-x) is negative
	private static int bitwiseMin (int a, int b) {
		//in java we don't need to check sizeof(int) because it's guaranteed to be a 
		//fixed 32 bits across platforms
		return (b + ((a-b) & ((a-b) >> 31)));
	}
	
	public static int smallest(int a, int b, int c) {
		return bitwiseMin(a, bitwiseMin(b,c));
	}
}
