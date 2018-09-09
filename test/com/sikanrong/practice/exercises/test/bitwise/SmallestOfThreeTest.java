package com.sikanrong.practice.exercises.test.bitwise;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.bitwise.SmallestOfThree;

public class SmallestOfThreeTest {
	
	@Test
	void smallestOfThreeTest () {
		assertEquals(4, SmallestOfThree.smallest(5,4,6));
		assertEquals(1, SmallestOfThree.smallest(2,200,1));
	}
	
}
