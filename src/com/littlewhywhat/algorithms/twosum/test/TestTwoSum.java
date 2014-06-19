package com.littlewhywhat.algorithms.twosum.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.io.txt.FileInputReader;
import com.littlewhywhat.algorithms.sort.SortingReader;
import com.littlewhywhat.algorithms.twosum.TwoSumAlgo;
import com.littlewhywhat.algorithms.twosum.TwoSumConfig;

public class TestTwoSum {

	private static final String FOLDER = "src/com/littlewhywhat/algorithms/twosum/test/input/";
	private static final String INPUT_SMALL = FOLDER + "2sumSmall.txt";
	private static final String INPUT_BIG = FOLDER + "2sum.txt";
	private final AbstractAlgorithm<TwoSumConfig, int[], Integer> algo = new TwoSumAlgo();
	private final FileInputReader<Void, int[]> reader = new SortingReader();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testSmall() {
		test(INPUT_SMALL, 0, 10);
	}
	
	@Test
	public void testBig() {
		test(INPUT_BIG, -10000, 10000);
	}

	private void test(String inputFilePath, int lowerBound, int upperBound) {
		reader.setInputFilePath(inputFilePath);
		reader.read();
		algo.setData(reader.getData());
		algo.setConfig(new TwoSumConfig(lowerBound, upperBound));
		algo.execute();
		System.out.println(algo.getOutput());
	}

}
