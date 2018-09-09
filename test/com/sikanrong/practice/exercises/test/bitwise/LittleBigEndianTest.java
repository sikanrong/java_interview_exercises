package com.sikanrong.practice.exercises.test.bitwise;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.bitwise.LittleBigEndian;

public class LittleBigEndianTest {

	@Test
	public void testLittleBigEndianness() {
		//in java we should always get big endian results
		int x = 0x12345678;
		
		assertEquals("12345678", LittleBigEndian.hexBytesFromRam(x));
		
	}
}
