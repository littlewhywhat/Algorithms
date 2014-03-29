package com.littlewhywhat.algorithms.baselinepredictors.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.BaselinePredictorsConfig;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.MatrixIndex;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.PredictionDataForAlgorithm;


public class TestBaselinePredictorsAlgorithm {

	private static final int LAMBDA = 15;
	private static final int NUMBER_TEST_VALUES = 4;
	private static final int MAX_RATING = 10;
	private static final int NUMBER_LEARN_VALUES = 5;
	BaselinePredictorsAlgorithm algorithm;
	private int[][] matrix;
	private MatrixIndex[] testIndices;
	private double[] values;
	private MatrixIndex[] learnIndices;

	@Before
	public void setUp() throws Exception {
		testIndices = new MatrixIndex[NUMBER_TEST_VALUES];
		learnIndices = new MatrixIndex[NUMBER_LEARN_VALUES];
		initTestIndices();
		initLearnIndices();
		matrix = new int[][] { { 9, 8, 0 }, { 0, 4, 6 }, { 0, 0, 7 } };
		algorithm = new BaselinePredictorsAlgorithm();
		algorithm.setConfig(new BaselinePredictorsConfig(LAMBDA, MAX_RATING));
		algorithm.setData(new PredictionDataForAlgorithm(matrix, testIndices));
	
		values = new double[] { 7.05, 6.68, 6.99, 6.68 };
		
	}

	private void initTestIndices() {
		testIndices[0] = new PredictionData.MatrixIndex(0, 2);
		testIndices[1] = new PredictionData.MatrixIndex(1, 0);
		testIndices[2] = new PredictionData.MatrixIndex(2, 0);
		testIndices[3] = new PredictionData.MatrixIndex(2, 1);
	}
	
	private void initLearnIndices() {
		learnIndices[0] = new PredictionData.MatrixIndex(0, 0);
		learnIndices[1] = new PredictionData.MatrixIndex(0, 1);
		learnIndices[2] = new PredictionData.MatrixIndex(1, 1);
		learnIndices[3] = new PredictionData.MatrixIndex(1, 2);
		learnIndices[4] = new PredictionData.MatrixIndex(2, 2);
	}

	@Test
	public void testCompute() {
		algorithm.execute();
		Assert.assertArrayEquals(values, algorithm.getOutput(), 0.14);
	}
}
