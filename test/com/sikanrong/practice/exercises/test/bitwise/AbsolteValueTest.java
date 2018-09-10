package com.sikanrong.practice.exercises.test.bitwise;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.bitwise.AbsoluteValue;



public class AbsolteValueTest {
	@Test
	void testAbsValue() {
		assertEquals(15, AbsoluteValue.absoluteValue(-15));
		assertEquals(15, AbsoluteValue.absoluteValue(15));
		assertEquals(22, AbsoluteValue.absoluteValue(-22));
		assertEquals(22, AbsoluteValue.absoluteValue(22));
	}
}
