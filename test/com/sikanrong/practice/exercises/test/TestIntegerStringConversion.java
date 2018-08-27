package com.sikanrong.practice.exercises.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.IntegerStringConversion;

public class TestIntegerStringConversion {
	@Test
	void testIntegerStringConversion() {
		assertEquals("198712", IntegerStringConversion.integerToString(198712));
		assertEquals("-37292933", IntegerStringConversion.integerToString(-37292933));
	}
	
	@Test
	void testStringIntegerConversion() {
		assertEquals(198712, IntegerStringConversion.stringToInteger("198712"));
		assertEquals(-37292933, IntegerStringConversion.stringToInteger("-37292933"));
	}
}
