package com.sikanrong.practice.exercises;
import java.util.regex.*;
import java.util.Stack;

public class ReverseWords {
	public static String reverseWords(String input) {
		Stack<String> words = new Stack<>();
		String output = new String();
		String currentWord = new String();
		
		for(int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			if(currentChar == ' ') {
				words.push(currentWord);
				currentWord = "";
			} else {
				currentWord += currentChar;
			}
		}
		
		if(currentWord.length() >= 1)
			words.push(currentWord);
		
		while(!words.empty()) {
			output += words.pop();
			if(!words.empty())
				output += ' ';
		}
		
		return output;
	}
}
