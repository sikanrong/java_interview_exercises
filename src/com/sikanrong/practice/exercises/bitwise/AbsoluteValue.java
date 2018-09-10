package com.sikanrong.practice.exercises.bitwise;

public class AbsoluteValue {
	public static int absoluteValue(int a) {
		int mask = a >> 31;
		return (a ^ mask) - mask;
	}
}
