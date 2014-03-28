package com.littlewhywhat.algorithms.baselinepredictors.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsReader;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.UserItemIndex;

public class TestBaselinePredictorsReader {
	
	private BaselinePredictorsReader reader;
	private UserItemIndex[] checkLearnEntries;
	private UserItemIndex[] checkTestEntries;
	private int[][] checkMatrix;
	
	@Before
	public void setUp() throws Exception {
		reader = new BaselinePredictorsReader();
		reader.setInputFilePath(TestBaselinePredictors.INPUT_FILE_PATH);
		checkLearnEntries = new UserItemIndex[5];
		checkTestEntries = new UserItemIndex[4];
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
		checkTestEntries[0] = new UserItemIndex(0, 2, 0);
		checkTestEntries[1] = new UserItemIndex(1, 0, 0);
		checkTestEntries[2] = new UserItemIndex(2, 0, 0);
		checkTestEntries[3] = new UserItemIndex(2, 1, 0);
	}

	private void initCheckLearnEntries() {
		checkLearnEntries[0] = new UserItemIndex(0, 0, 9);
		checkLearnEntries[1] = new UserItemIndex(0, 1, 8);
		checkLearnEntries[2] = new UserItemIndex(1, 1, 4);
		checkLearnEntries[3] = new UserItemIndex(1, 2, 6);
		checkLearnEntries[4] = new UserItemIndex(2, 2, 7);
			
	}

	@Test
	public void test() {
		reader.read();
		testPredictionConfig();
		testPredictionData();
	}

	private void testPredictionData() {
		Assert.assertArrayEquals(checkLearnEntries, reader.getData().getLearnEntries());
		Assert.assertArrayEquals(checkTestEntries, reader.getData().getTestEntries());
		Assert.assertArrayEquals(checkMatrix, reader.getData().getMatrix());
	}

	private void testPredictionConfig() {
		Assert.assertEquals(10, reader.getConfig(), 0);
	}

}
