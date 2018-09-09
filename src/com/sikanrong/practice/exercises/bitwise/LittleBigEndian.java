package com.sikanrong.practice.exercises.bitwise;

public class LittleBigEndian {
	public static String hexBytesFromRam(int x) {
		String out = new String();
		//reverse loop because we want representation in memory to correspond with 
		//representation in string. E.g. leftmost byte should be leftmost in string.
		for(int i = 3; i >= 0; i--) { //an int has 32 bits = 4 bytes.
			byte hexpair = (byte)((x >> (i*8)));
			out += String.format("%1$2x", hexpair);
		}
		
		return out;
	}
}
