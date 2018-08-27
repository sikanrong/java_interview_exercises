package com.sikanrong.practice.exercises.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.ReverseWords;

public class ReverseWordsTest {

	@Test
	void reverseWordsTest() {
		String actual = ReverseWords.reverseWords("Do or do not, there is no try.");
		String expected = "try. no is there not, do or Do";
		
		assertEquals(expected, actual);
	}
}
