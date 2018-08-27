package com.sikanrong.practice.exercises.test.stringsarrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.stringsarrays.FindNonRepeated;

class FindNonRepeatedTest {

	@Test
	void testFindNonRepeated() {
		char _c = FindNonRepeated.findNonRepeated("teeter");
		assertEquals(_c, 'r');
		_c = FindNonRepeated.findNonRepeated("stutters");
		assertEquals(_c, 'u');
		_c = FindNonRepeated.findNonRepeated("teething");
		assertEquals(_c, 'h');
	}
}
