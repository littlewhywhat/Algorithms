package com.littlewhywhat.algorithms.baselinepredictors.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsReader;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.UserItemEntry;

public class TestBaselinePredictorsReader {
	
	private BaselinePredictorsReader reader;
	private UserItemEntry[] checkLearnEntries;
	private UserItemEntry[] checkTestEntries;
	
	@Before
	public void setUp() throws Exception {
		reader = new BaselinePredictorsReader();
		reader.setInputFilePath(TestBaselinePredictors.INPUT_FILE_PATH);
		checkLearnEntries = new UserItemEntry[5];
		checkTestEntries = new UserItemEntry[4];
		initCheckLearnEntries();
		initCheckTestEntries();
	}

	private void initCheckTestEntries() {
		checkTestEntries[0] = new UserItemEntry(0, 2, 0);
		checkTestEntries[1] = new UserItemEntry(1, 0, 0);
		checkTestEntries[2] = new UserItemEntry(2, 0, 0);
		checkTestEntries[3] = new UserItemEntry(2, 1, 0);
	}

	private void initCheckLearnEntries() {
		checkLearnEntries[0] = new UserItemEntry(0, 0, 9);
		checkLearnEntries[1] = new UserItemEntry(0, 1, 8);
		checkLearnEntries[2] = new UserItemEntry(1, 1, 4);
		checkLearnEntries[3] = new UserItemEntry(1, 2, 6);
		checkLearnEntries[4] = new UserItemEntry(2, 2, 7);
			
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
		
	}

	private void testPredictionConfig() {
		Assert.assertEquals(10, reader.getConfig().getMaxRateValue());
		Assert.assertEquals(3, reader.getConfig().getNumOfItems());
		Assert.assertEquals(3, reader.getConfig().getNumOfUsers());
	}

}
