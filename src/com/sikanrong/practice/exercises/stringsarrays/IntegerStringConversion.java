package com.sikanrong.practice.exercises.stringsarrays;
import java.util.Stack;

public class IntegerStringConversion {
	public static String integerToString(int input) {
		String output = new String();
		Stack<Character> input_chars = new Stack<>();
		boolean isNegative = input < 0;
		input = Math.abs(input);
		while(input > 0) {
			input_chars.push((char)((input % 10) + 48));
			input = input / 10;
		}
		
		if(isNegative)
			output += '-';

		while(!input_chars.empty())
			output += input_chars.pop();
		
		return output;
	}
	
	public static int stringToInteger(String input) {
		int output = 0;
		boolean isNegative = (input.charAt(0) == '-');
		
		for(int i = input.length() - 1; i >= (isNegative? 1 : 0); i--) {
			int decimalPlace = input.length() - 1 - i;
			int digit = (int)input.charAt(i) - 48;
			output += (int)Math.pow(10, decimalPlace) * digit;
		}
		
		return (isNegative)? -output : output;
	}
	
}
