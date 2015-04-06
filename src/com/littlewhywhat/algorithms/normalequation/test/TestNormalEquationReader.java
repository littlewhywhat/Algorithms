package com.littlewhywhat.algorithms.normalequation.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.normalequation.NormalEquationReader;

public class TestNormalEquationReader {

	private NormalEquationReader reader;
	private Integer[] config;
	private double[][] learnValues;
	private double[] learnResults;
	private double[][] testValues;

	@Before
	public void setUp() throws Exception {
		reader = new NormalEquationReader();
		reader.setInputFilePath(TestNormalEquation.INPUT_FILE_PATH);
		config = new Integer[] { 10, 3, 3 };
		learnValues = new double[][] { { 1, 0, 0 }, {1, 0, 1 }, { 1, 1, 1 }, { 1,1, 2 },
				{ 1, 2, 2 } };
		learnResults = new double[] { 9, 8, 4, 6, 7 };
		testValues = new double[][] { { 1, 0, 2}, { 1, 1, 0 }, { 1, 2, 0}, { 1, 2, 1 } };
	}

	@Test
	public void testRead() {
		reader.read();
		Assert.assertArrayEquals(config, reader.getConfig());
		Assert.assertArrayEquals(learnValues, reader.getData().getLearnValues()
				.getData());
		Assert.assertArrayEquals(testValues, reader.getData().getTestValues()
				.getData());
		Assert.assertArrayEquals(learnResults, reader.getData().getLearnResults().toArray(), 0);
	}

}
