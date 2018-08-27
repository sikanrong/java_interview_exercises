package com.sikanrong.practice.exercises.test.recursion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.recursion.BinarySearch;

public class BinarySearchTest {

	@Test
	void testBinarySearch() {
		Integer sortedInts[] = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
		BinarySearch<Integer> bs = new BinarySearch<>(sortedInts);
		assertEquals(-1, bs.search(new Integer(3)));
		assertEquals(4, bs.search(new Integer(16)));
		assertEquals(7, bs.search(new Integer(128)));
		assertEquals(-1, bs.search(new Integer(127)));
	}
}
