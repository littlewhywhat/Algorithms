package com.littlewhywhat.algorithms.baselinepredictors.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.BaselinePredictorsConfig;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.BaselinePredictorsData;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.MatrixIndex;


public class TestBaselinePredictorsAlgorithm {

	private static final int LAMBDA = 15;
	private static final int NUMBER_TEST_VALUES = 4;
	private static final int MAX_RATING = 10;
	BaselinePredictorsAlgorithm algorithm;
	private int[][] matrix;
	private MatrixIndex[] testIndices;
	private double[] values;

	@Before
	public void setUp() throws Exception {
		testIndices = new MatrixIndex[NUMBER_TEST_VALUES];
		initTestIndices();
		matrix = new int[][] { { 9, 8, 0 }, { 0, 4, 6 }, { 0, 0, 7 } };
		algorithm = new BaselinePredictorsAlgorithm();
		algorithm.setConfig(new BaselinePredictorsConfig(LAMBDA, MAX_RATING));
		algorithm.setData(new BaselinePredictorsData(matrix, testIndices));
	
		values = new double[] { 7.05, 6.68, 6.99, 6.68 };
		
	}

	private void initTestIndices() {
		testIndices[0] = new MatrixIndex(0, 2);
		testIndices[1] = new MatrixIndex(1, 0);
		testIndices[2] = new MatrixIndex(2, 0);
		testIndices[3] = new MatrixIndex(2, 1);
	}

	@Test
	public void testCompute() {
		algorithm.execute();
		Assert.assertArrayEquals(values, algorithm.getOutput(), 0.14);
	}
}
