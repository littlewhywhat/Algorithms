package com.littlewhywhat.algorithms.baselinepredictors.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsComputer;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.UserItemEntry;

public class TestBaselinePredictorsComputer {

	private static final int LAMBDA = 15;
	private static final int NUMBER_TEST_VALUES = 4;
	private static final int MAX_RATING = 10;
	BaselinePredictorsComputer computer;
	private int[][] matrix;
	private UserItemEntry[] testValues;
	private double[] values;

	@Before
	public void setUp() throws Exception {
		matrix = new int[][] { { 9, 8, 0 }, { 0, 4, 6 }, { 0, 0, 7 } };
		computer = new BaselinePredictorsComputer();
		computer.setLambda(LAMBDA);
		computer.setMatrix(matrix);
		computer.setMaxRating(MAX_RATING);
		testValues = new UserItemEntry[NUMBER_TEST_VALUES];
		values = new double[] { 7.05, 6.68, 6.99, 6.68 };
		initTestValues();
	}

	private void initTestValues() {
		testValues[0] = new UserItemEntry(0, 2, 0);
		testValues[1] = new UserItemEntry(1, 0, 0);
		testValues[2] = new UserItemEntry(2, 0, 0);
		testValues[3] = new UserItemEntry(2, 1, 0);
	}

	@Test
	public void testCompute() {
		Assert.assertArrayEquals(values, computer.compute(testValues), 0.14);
	}
}
