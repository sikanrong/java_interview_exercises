package com.sikanrong.practice.exercises.test.concurrency;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.sikanrong.practice.exercises.concurrency.MatrixMultiplyParallel;
import com.sikanrong.practice.exercises.concurrency.MatrixMultiplyParallel.Matrix;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatrixMultiplyParallelTest {
	
	@Test 
	void testMatrixMultiplyEven() {
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
		
		assertArrayEquals(expected.toArray(), actual.toArray());
		
	}
	
	@Test 
	void testMatrixMultiplyOdd() {
		Integer[][] ar_a = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};
		
		Integer[][] ar_b = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};
		
		Integer[][] ar_e = {
			{30, 36, 42}, 
			{66, 81, 96}, 
			{102, 126, 150}
		};
		
		Matrix<Integer> a = new Matrix<>(ar_a);
		Matrix<Integer> b = new Matrix<>(ar_b);
		Matrix<Integer> actual = MatrixMultiplyParallel.multiply(a, b);
		Matrix<Integer> expected = new Matrix<>(ar_e);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
		
	}
	
	
	@Test 
	void testMatrixMultiplyEven2() {
		Integer[][] ar_a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}, {13,14,15,16}};
		Integer[][] ar_b = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}, {13,14,15,16}};
		
		Integer[][] ar_e = {{90, 100, 110, 120}, {202, 228, 254, 280}, {314, 356, 398, 440}, {426, 484, 542, 600}};
		
		Matrix<Integer> a = new Matrix<>(ar_a);
		Matrix<Integer> b = new Matrix<>(ar_b);
		Matrix<Integer> actual = MatrixMultiplyParallel.multiply(a, b);
		Matrix<Integer> expected = new Matrix<>(ar_e);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
		
	}
	
	@Test 
	void testMatrixMultiplyNonSquare() {
		Integer[][] ar_a = {{1, 2, 3}, {5, 6, 7}, {9,10,11}, {13,14,15}};
		Integer[][] ar_b = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}};
		
		Integer[][] ar_e = {{38, 44, 50, 56}, {98, 116, 134, 152}, {158, 188, 218, 248}, {218, 260, 302, 344}};
		
		Matrix<Integer> a = new Matrix<>(ar_a);
		Matrix<Integer> b = new Matrix<>(ar_b);
		Matrix<Integer> actual = MatrixMultiplyParallel.multiply(a, b);
		Matrix<Integer> expected = new Matrix<>(ar_e);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
		
	}
	
	
}
