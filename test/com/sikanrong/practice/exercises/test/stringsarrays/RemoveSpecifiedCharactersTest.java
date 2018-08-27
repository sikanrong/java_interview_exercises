package com.sikanrong.practice.exercises.test.stringsarrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.stringsarrays.RemoveSpecifiedCharacters;

public class RemoveSpecifiedCharactersTest {

	@Test
	void testRemoveSpecifiedCharacters() {
		String input = "Battle of the Vowels: Hawaii vs. Grozny";
		String removal = "aeiou";
		String actual = RemoveSpecifiedCharacters.removeSpecifiedCharacters(input, removal);
		String expected = "Bttl f th Vwls: Hw vs. Grzny";
		assertEquals(actual, expected);
	}
}
