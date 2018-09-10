package com.sikanrong.practice.exercises.test.bitwise;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.bitwise.XorFromOtherOperations;

public class XorFromOtherOperationsTest {

	@Test
	void testXor() {
		assertEquals((272 ^ 187), XorFromOtherOperations.xor(272, 187));
		assertEquals((732 ^ -665), XorFromOtherOperations.xor(732, -665));
		assertEquals((-912 ^ -342), XorFromOtherOperations.xor(-912, -342));
	}
}
