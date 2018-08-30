package com.sikanrong.practice.exercises.recursion;

public class GenerateCombinations {
	private String input;
	public String[] output;
	private int nextIndex = 0;
	private boolean verbose = false;
	
	public GenerateCombinations(String input) {
		this(input, false);
	}
	
	public GenerateCombinations(String input, boolean verbose){
		this.input = input;
		this.verbose = verbose;
		this.output = new String[(int)Math.pow(2, input.length()) - 1];
		
		this.combine(new String(), 0);
	}
	
	public void combine(String prefix, int from) {
		//First write the stop case
		if(from >= input.length()) {
			//stop the recursive loop.
			return;
		}
		
		for(int i = from; i < this.input.length(); i++) {
			String newPrefix = prefix + this.input.charAt(i);
			this.output[nextIndex++] = newPrefix;
			if(this.verbose) {
				System.out.println(newPrefix);
			}
			this.combine(newPrefix, i + 1);
		}
		
	};
	
}
