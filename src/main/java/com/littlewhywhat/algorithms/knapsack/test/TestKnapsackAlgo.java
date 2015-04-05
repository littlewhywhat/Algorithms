package com.littlewhywhat.algorithms.knapsack.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.knapsack.KnapsackAlgo;
import com.littlewhywhat.algorithms.knapsack.KnapsackItem;
import com.littlewhywhat.algorithms.knapsack.KnapsackReader;
import com.littlewhywhat.algorithms.knapsack.SimpleItem;

public class TestKnapsackAlgo {

	private static final String FOLDER = "src/com/littlewhywhat/algorithms/knapsack/test/input/";
	private static final String INPUT = FOLDER + "knapsack.txt";
	private static final String INPUT_SMALL = FOLDER + "knapsack_small.txt";
	private static final String INPUT_BIG = FOLDER + "knapsack_big.txt";
	
	private static final KnapsackItem[] INPUT_DATA = new KnapsackItem[] {
			new SimpleItem(3, 4), new SimpleItem(2, 3), new SimpleItem(4, 2),
			new SimpleItem(4, 3) };
	private static final int INPUT_CONFIG = 6;
	private static final int ANSWER = 8;
	private static final int ANSWER_SMALL = 2493893;
	private static final int ANSWER_BIG = 4243395;
	private KnapsackAlgo algo;
	private KnapsackReader reader;

	@Before
	public void setUp() throws Exception {
		reader = new KnapsackReader();
		algo = new KnapsackAlgo();

	}

	@Test
	public void test() {
		test(INPUT, ANSWER);
		Assert.assertArrayEquals(INPUT_DATA, algo.getData().toArray());
		Assert.assertEquals(INPUT_CONFIG, algo.getConfig().intValue());
	}

	@Test
	public void testSmall() {
		test(INPUT_SMALL, ANSWER_SMALL);
	}

	@Test
	public void testBig() {
		test(INPUT_BIG, ANSWER_BIG);
	}

	public void test(String inputFilePath, int answer) {
		reader.setInputFilePath(inputFilePath);
		reader.read();
		algo.setConfig(reader.getConfig());
		algo.setData(reader.getData());
		algo.execute();
		Assert.assertEquals(answer, algo.getOutput().intValue());
	}

}
