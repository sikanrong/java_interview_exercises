package com.sikanrong.practice.exercises.test.recursion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.recursion.GeneratePermutations;

public class GeneratePermutationsTest {
	@Test
	void testGeneratePermuations() {
		GeneratePermutations p = new GeneratePermutations("abc");
		String[] expected = {"abc", "acb", "bac", "bca", "cab", "cba"};
		assertArrayEquals(expected, p.permutations);
	}
}
