package com.littlewhywhat.algorithms.baselinepredictors.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsReader;
import com.littlewhywhat.algorithms.baselinepredictors.MatrixIndex;
import com.littlewhywhat.datastructure.Array;

public class TestBaselinePredictorsReader {

	private BaselinePredictorsReader reader;
	private MatrixIndex[] checkLearnEntries;
	private MatrixIndex[] checkTestEntries;
	private int[][] checkMatrix;

	private final String INPUT_FILE_PATH = TestBaselinePredictors.FOLDER
			+ "inputForReaderTest.txt";

	@Before
	public void setUp() throws Exception {
		reader = new BaselinePredictorsReader();
		reader.setInputFilePath(INPUT_FILE_PATH);
		checkLearnEntries = new MatrixIndex[5];
		checkTestEntries = new MatrixIndex[4];
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
		checkTestEntries[0] = new MatrixIndex(0, 2);
		checkTestEntries[1] = new MatrixIndex(1, 0);
		checkTestEntries[2] = new MatrixIndex(2, 0);
		checkTestEntries[3] = new MatrixIndex(2, 1);
	}

	private void initCheckLearnEntries() {
		checkLearnEntries[0] = new MatrixIndex(0, 0);
		checkLearnEntries[1] = new MatrixIndex(0, 1);
		checkLearnEntries[2] = new MatrixIndex(1, 1);
		checkLearnEntries[3] = new MatrixIndex(1, 2);
		checkLearnEntries[4] = new MatrixIndex(2, 2);

	}

	@Test
	public void test() {
		reader.read();
		testPredictionConfig();
		testPredictionData();
	}

	private void testPredictionData() {
		Assert.assertArrayEquals( checkLearnEntries, reader.getData().getLearnIndices());
		checkArray(checkTestEntries, reader.getData().getTestIndices());
		Assert.assertArrayEquals(checkMatrix, reader.getData().getMatrix());
	}

	private void testPredictionConfig() {
		Assert.assertEquals(10, reader.getConfig(), 0);
	}
	
	private void checkArray(MatrixIndex[] testArray, Array<MatrixIndex> array) {
		for (int i = 0; i < testArray.length; i++)
			Assert.assertEquals(testArray[i], array.get(i));
	}

}
