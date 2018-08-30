package com.sikanrong.practice.exercises.recursion;

public class TelephoneWords {
	public String output[];
	
	private int number[];
	private boolean verbose;
	private int nextIndex = 0;
	
	//The exercise from the book simplifies things by omitting the letter 'q'
	private String alphabet = "abcdefghijklmnoprstuvwxyz";
	
	public TelephoneWords(int[] number) {
		this(number, false);
	}
	
	public TelephoneWords(int[] number, boolean verbose) {
		this.number = number;
		this.verbose = verbose;
		
		//find the total to set the size of output[]
		//set output[] based on worst-case total (there are no 1s or 0s)
		this.output = new String[(int)Math.pow(3, number.length)];

		generateCombinations(new String(), 0);
	}
	
	private char getTelephoneKeyChar(int key, int place) {
		if(key <= 1)
			return Integer.toString(key).charAt(0);
		
		
		int startIdx = 3 * (key - 2);
		char ret = alphabet.charAt(startIdx + place);
		return ret;
	}
	
	private void generateCombinations(String prefix, int keyIndex) {
		//stop case
		if(keyIndex >= number.length) {
			if(this.verbose)
				System.out.println(prefix);
			this.output[nextIndex++] = prefix;
			return;
		}
		
		
		int currentDigit = this.number[keyIndex];
		int iterateLetters = (currentDigit <= 1)? 1 : 3;
		
		for(int i = 0; i < iterateLetters; i++) {
			String newPrefix = prefix + this.getTelephoneKeyChar(currentDigit, i);
			this.generateCombinations(newPrefix, keyIndex + 1);
		}
	};
}
