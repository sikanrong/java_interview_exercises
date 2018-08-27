package com.sikanrong.practice.exercises.stringsarrays;

import java.util.HashMap;

public class FindNonRepeated {
	public static char findNonRepeated(String input) {
		HashMap<Character, Integer> letterCounts = new HashMap<Character, Integer>(26, 1);
		for(int i = 0; i < input.length(); i++) {
			char _k = input.charAt(i);
			if(letterCounts.containsKey(_k)) {
				int count = (int)letterCounts.get(_k);
				letterCounts.put(_k, ++count);
			}else {
				letterCounts.put(_k, 1);
			}
		}
		
		char _k = '\0';
		for(int i = 0; i < input.length(); i++) {
			_k = input.charAt(i);
			int count = (int)letterCounts.get(_k);
			if(count == 1)
				break;
		}
		
		return _k;
	}
}
