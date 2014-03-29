package com.littlewhywhat.algorithms.baselinepredictors.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsReader;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData;

public class TestBaselinePredictorsReader {
	
	private BaselinePredictorsReader reader;
	private PredictionData.MatrixIndex[] checkLearnEntries;
	private PredictionData.MatrixIndex[] checkTestEntries;
	private int[][] checkMatrix;
	
	@Before
	public void setUp() throws Exception {
		reader = new BaselinePredictorsReader();
		reader.setInputFilePath(TestBaselinePredictors.INPUT_FILE_PATH);
		checkLearnEntries = new PredictionData.MatrixIndex[5];
		checkTestEntries = new PredictionData.MatrixIndex[4];
		checkMatrix = new int[3][3];
		initCheckLearnEntries();
		initCheckTestEntries();
		initCheckMatrix();
	}

	private void initCheckMatrix() {
		checkMatrix[0][0] = 9;
		checkMatrix[0][1] = 8;
		checkMatrix[1][1] = 4;
		checkMatrix[1][2] = 6;
		checkMatrix[2][2] = 7;
	}

	private void initCheckTestEntries() {
		checkTestEntries[0] = new PredictionData.MatrixIndex(0, 2);
		checkTestEntries[1] = new PredictionData.MatrixIndex(1, 0);
		checkTestEntries[2] = new PredictionData.MatrixIndex(2, 0);
		checkTestEntries[3] = new PredictionData.MatrixIndex(2, 1);
	}

	private void initCheckLearnEntries() {
		checkLearnEntries[0] = new PredictionData.MatrixIndex(0, 0);
		checkLearnEntries[1] = new PredictionData.MatrixIndex(0, 1);
		checkLearnEntries[2] = new PredictionData.MatrixIndex(1, 1);
		checkLearnEntries[3] = new PredictionData.MatrixIndex(1, 2);
		checkLearnEntries[4] = new PredictionData.MatrixIndex(2, 2);
			
	}

	@Test
	public void test() {
		reader.read();
		testPredictionConfig();
		testPredictionData();
	}

	private void testPredictionData() {
		Assert.assertArrayEquals(checkLearnEntries, reader.getData().getLearnIndices());
		Assert.assertArrayEquals(checkTestEntries, reader.getData().getTestIndices());
		Assert.assertArrayEquals(checkMatrix, reader.getData().getMatrix());
	}

	private void testPredictionConfig() {
		Assert.assertEquals(10, reader.getConfig(), 0);
	}

}
