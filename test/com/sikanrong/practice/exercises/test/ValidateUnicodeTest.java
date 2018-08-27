package com.sikanrong.practice.exercises.test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import com.sikanrong.practice.exercises.ValidateUnicode;

public class ValidateUnicodeTest {
	//Here I'm going to create a (valid) UTF-8 string from java's typical UTF-16 strings
	
	@Test
	void testUnicodeValidatorASCII() {
		//The first example will only contain ASCII chars
		String basicExample = "Hello. I'm a basic UTF-8 String using only ASCII text";
		assertEquals(true, ValidateUnicode.validate(
			StandardCharsets.UTF_8.encode(basicExample)
		));
	}
	
	@Test 
	void testUnicodeValidatorJAP() {
		//The next will use Japanese chars, which should be well outside of 
		//ASCII range in the Unicode set.
		String advancedExample = "こんにちは。 私は、ASCIIテキストのみを使用する基本的なUTF-8文字列です";
		assertEquals(true, ValidateUnicode.validate(
			StandardCharsets.UTF_8.encode(advancedExample)
		));
	}
	
	@Test
	void testNotEnoughTrailingBytes() {
		//pass in something that is almost a unicode string
		ByteBuffer bb = ByteBuffer.allocate(4);
		byte nonUnicode[] = {(byte)0b11110000, (byte)0b10000000, (byte)0b10000000};
		bb.put(nonUnicode);
		
		assertEquals(false, ValidateUnicode.validate(bb));
	}
	
	@Test
	void testErroneousFirstBitSet() {
		//pass in something that is almost a unicode string
		ByteBuffer bb = ByteBuffer.allocate(4);
		byte nonUnicode[] = {(byte)0b10000110, (byte)0b10101010, (byte)0b00000011};
		bb.put(nonUnicode);
		
		assertEquals(false, ValidateUnicode.validate(bb));
	}
	
	@Test
	void testMixedEncoding() {
		//pass in something that is almost a unicode string
		ByteBuffer bb = ByteBuffer.allocate(4);
		byte validUnicode[] = {(byte)0b11000000, (byte)0b10000000, (byte)0b00000011};
		bb.put(validUnicode);
		
		assertEquals(true, ValidateUnicode.validate(bb));
	}
	
}
