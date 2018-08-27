package com.sikanrong.practice.exercises;

import java.nio.ByteBuffer;

//Valid UTF-8 byte patterns:
//0xxxxxxx 
//110xxxxx 10xxxxxx 
//1110xxxx 10xxxxxx 10xxxxxx 
//11110xxx 10xxxxxx 10xxxxxx 10xxxxxx

public class ValidateUnicode {
		public static boolean validate(ByteBuffer input) {
			
			byte currentByte = (byte)0b00000000;
			int bufferLimit = input.limit();
			boolean output = true;
			int trailingBytesLeft = 0;
			
			for(int i = 0; i < bufferLimit; i++) {
				currentByte = input.get(i);
				
				if(trailingBytesLeft == 0) {
					
					byte firstbitMask = (byte)(0b1 << 7);
					byte firstbit = (byte)((currentByte & firstbitMask) >> 7);
					
					if(firstbit == 0) {
						//ASCII case is generally always valid. 
						//If the first bit is zero, any arrangement of the seven
						//following bits would lead to a valid ASCII character.
						//In this sense, there is nothing else to validate.
						continue;
					}
					
					//Next we check the various valid types of leading bits
					//in (what should be) the leading byte
					
					byte mask = (byte)0b11000000;
					if((mask & currentByte) != mask) {
						output = false;
						break;
					}
					
					for(int j = 1; j <= 3; j++) {
						byte nMask = (byte)(mask >>> (j));
						byte cMask = (byte)(mask >>> (j-1));
						if((nMask & currentByte) == cMask) {
							trailingBytesLeft = j;
							break;
						}
					}
					
					if(trailingBytesLeft == 0) {
						output = false;
						break;
					}
					
				}else {
					//now validate the trailing bytes. Make sure each of them 
					//have the proper leading bits.
					byte mask = (byte)0b11000000;
					if((mask & currentByte) != (byte)0b10000000) {
						output = false;
						break;
					}
					
					trailingBytesLeft--;
				}
				
				
			}
			
			if(trailingBytesLeft > 0) 
				output = false;
			
			return output;
		}
}
