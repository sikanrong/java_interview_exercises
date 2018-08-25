package com.sikanrong.practice.exercises;
import java.util.HashMap;

public class RemoveSpecifiedCharacters {
	public static String removeSpecifiedCharacters(String input, String removal) {
		String output = new String();
		HashMap<Character, Object> removalMap = new HashMap<>(removal.length(), 1);
		for(int i = 0; i < removal.length(); i++) {
			removalMap.put(removal.charAt(i), null);
		}
		for(int i = 0; i < input.length(); i++) {
			char _k = input.charAt(i);
			if(!removalMap.containsKey(_k)) {
				output += _k;
			}
		}
		return output;
	}
}
