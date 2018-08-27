package com.sikanrong.practice.exercises.recursion;

public class BinarySearch<T extends Comparable<T>> {
	private T[] sortedArray;
	public BinarySearch(T[] sorted_array) {
		this.setData(sorted_array);
	}
	
	public void setData(T[] sorted_array){
		this.sortedArray = sorted_array;
	}
	
	private int searchHelper(T input, int l, int u) {
		if(u < l) 
			return -1; //stop case
		
		int m = ((l + u) / 2);
		T middle = this.sortedArray[m];
		switch(input.compareTo(middle)) {
			case 0:
				return m;
			case 1: 
				return searchHelper(input, m+1, u);
			case -1:
				return searchHelper(input, l, m-1);
				
		};
		
		return -1; //safety in case compareTo returns erroneously.
	}
	
	public int search(T input) {
		return searchHelper(input, 0, this.sortedArray.length - 1);
	}
}
