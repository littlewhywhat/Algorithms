package com.littlewhywhat.algorithms.knapsack.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.knapsack.KnapsackAlgo;
import com.littlewhywhat.algorithms.knapsack.KnapsackReader;

public class TestKnapsackAlgo {

	private static final String FOLDER = "src/com/littlewhywhat/algorithms/knapsack/test/input/";
	private static final String INPUT = FOLDER + "knapsack.txt";
	private static final String INPUT_SMALL = FOLDER + "knapsack_small.txt";
	private static final String INPUT_BIG = FOLDER + "knapsack_big.txt";
	private KnapsackAlgo algo;
	private KnapsackReader reader;
	
	@Before
	public void setUp() throws Exception {
		reader = new KnapsackReader();
		algo = new KnapsackAlgo();
		
	}

	@Test
	public void test() {
		test(INPUT);
	}
	
	@Test
	public void testSmall() {
		test(INPUT_SMALL);
	}
	
	@Test
	public void testBig() {
		test(INPUT_BIG);
	}
	
	public void test(String inputFilePath) {
		reader.setInputFilePath(INPUT);
		reader.read();
		algo.setConfig(reader.getConfig());
		algo.setData(reader.getData());
		algo.execute();
		System.out.println(algo.getOutput());
	}

}
