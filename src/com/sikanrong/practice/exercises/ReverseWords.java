package com.sikanrong.practice.exercises;
import java.util.regex.*;
import java.util.Stack;

public class ReverseWords {
	public static String reverseWords(String input) {
		String output = new String();
		Pattern p = Pattern.compile("(([^\\s]+)(\\s|$))");
		Matcher m = p.matcher(input);
		Stack<String> words = new Stack<>();
		while(m.find()) {
			String word = m.group(2);
			words.push(word);
		}
		
		while(!words.empty()) {
			output += words.pop();
			if(!words.empty())
				output += ' ';
		}
		
		return output;
	}
}
