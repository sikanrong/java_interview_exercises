package com.sikanrong.practice.exercises.test.concurrency;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.sikanrong.practice.exercises.concurrency.MatrixMultiplyParallel;
import com.sikanrong.practice.exercises.concurrency.MatrixMultiplyParallel.Matrix;

public class MatrixMultiplyParallelTest {
	@SuppressWarnings("unused")
	@Test 
	void testMatrixMultiply() {
		Integer[][] ar_a = {
			{1,2},
			{3,4}
		};
		
		Integer[][] ar_b = {
			{2,0},
			{1,2}
		};
		
		Integer[][] ar_e = {
			{4,4},
			{10,8}
		};
		
		Matrix<Integer> a = new Matrix<>(ar_a);
		Matrix<Integer> b = new Matrix<>(ar_b);
		Matrix<Integer> actual = MatrixMultiplyParallel.multiply(a, b);
		Matrix<Integer> expected = new Matrix<>(ar_e);
		
		assertArrayEquals(actual.toArray(), expected.toArray());
		
	}
}
