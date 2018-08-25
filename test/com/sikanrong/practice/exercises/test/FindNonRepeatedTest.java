package com.sikanrong.practice.exercises.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.FindNonRepeated;

class FindNonRepeatedTest {

	@Test
	void testFindNonRepeated() {
		char _c = FindNonRepeated.findNonRepeated("teeter");
		assertEquals(_c, 'r');
	}
}
