package com.sikanrong.practice.exercises.recursion;

public class GeneratePermutations {
	private char[] set;
	public String[] permutations;
	private int nextIndex = 0;
	private boolean verbose = false;
	
	public GeneratePermutations(String input) {
		this(input, false);
	}
	
	public GeneratePermutations(String input, boolean verbose) {
		this.set = input.toCharArray();
		this.permutations = new String[factorial(set.length)];
		this.verbose = verbose;
		
		//we know that there will be n! permutations of a given 
		//string set of cardinality n, if repetition is not allowed.
		generatePermutations(new String(), 0);
	}
	
	//recursive factorial function
	private static int factorial(int i) {
		if(i == 0)
			return 1;
		return i * factorial(i - 1);
	}
	
	private void generatePermutations ( String prefix, int usedmask ) {
		//stop case
		if(usedmask == (int)Math.pow(2, this.set.length) - 1) { //if all bits are set
			this.permutations[nextIndex++] = prefix;
			if(verbose)
				System.out.println(prefix);
		} 
		
		for(int i = 0; i < set.length; i++) {
			if(((usedmask >> i) & 1) == 1)
				continue;
			generatePermutations((prefix + set[i]), (usedmask|(0b1 << i)));
		}
	}
	
}
