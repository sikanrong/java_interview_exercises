package com.sikanrong.practice.exercises.test.recursion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.recursion.TelephoneWords;

import java.util.Arrays;

public class TelephoneWordsTest {

	@Test
	void testTelephoneWords() {
		int number[] = {8,6,6};
		TelephoneWords tw = new TelephoneWords(number);
		String expected[] = {
			"tmm",
			"tmn",
			"tmo",
			"tnm",
			"tnn",
			"tno",
			"tom",
			"ton",
			"too",
			"umm",
			"umn",
			"umo",
			"unm",
			"unn",
			"uno",
			"uom",
			"uon",
			"uoo",
			"vmm",
			"vmn",
			"vmo",
			"vnm",
			"vnn",
			"vno",
			"vom",
			"von",
			"voo"
		};
		
		assertArrayEquals(expected, tw.output);
	}
	
	@Test
	void testSpecialCaseTelephoneWords(){
		int number[] = {0,6,1};
		TelephoneWords tw = new TelephoneWords(number);
		String expected[] = {"0m1", "0n1", "0o1"};
		
		//Have to truncate the result array, which is always initialized
		//to such a size so as to accommodate the worst case.
		assertArrayEquals(expected, Arrays.copyOfRange(tw.output, 0, 3));
	}
	
}
