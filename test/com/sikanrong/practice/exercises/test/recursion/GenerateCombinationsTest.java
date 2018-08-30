package com.sikanrong.practice.exercises.test.recursion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.recursion.GenerateCombinations;

public class GenerateCombinationsTest {

	@Test
	void generateCombinations(){
		GenerateCombinations gc = new GenerateCombinations("wxyz");
		String expected[] = {
			"w","wx","wxy","wxyz","wxz","wy","wyz","wz",
			"x","xy","xyz","xz",
			"y","yz",
			"z"
		};
		assertArrayEquals(expected, gc.output);
	}
}
