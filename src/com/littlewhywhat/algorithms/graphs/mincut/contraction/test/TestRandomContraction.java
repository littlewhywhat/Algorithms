package com.littlewhywhat.algorithms.graphs.mincut.contraction.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.mincut.contraction.GraphReader;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.RandomContraction;

public class TestRandomContraction {

	private static final int ANSWER_BIG = 17;
	private static final int ANSWER_SMALL_ONE = 2;
	private static final int ANSWER_SMALL_TWO = 1;
	public static final String FOLDER = "src/com/littlewhywhat/algorithms/graphs/mincut/contraction/test/input/";
	private static final String INPUT_FILE_PATH_BIG = FOLDER + "kargerMinCut.txt";
	private static final String INPUT_FILE_PATH_SMALL_ONE = FOLDER + "smallGraphOne.txt";
	private static final String INPUT_FILE_PATH_SMALL_TWO = FOLDER + "smallGraphTwo.txt";
	private RandomContraction randomContraction;
	private GraphReader reader;
	
	@Before
	public void setUp() throws Exception {
		this.randomContraction = new RandomContraction();
		this.reader = new GraphReader();
		
	}

	private void test(int answer) {
		int min = 999999999;
		reader.read();
		for (int i = 0; i < 5000; i++) {
			randomContraction.setData(reader.getData());
			randomContraction.execute();
			if (randomContraction.getOutput() < min)
				min = randomContraction.getOutput();
		}
		Assert.assertEquals(answer, min);		
	}

	@Test
	public void testBig() {
		this.reader.setInputFilePath(INPUT_FILE_PATH_BIG);
		test(ANSWER_BIG);
	}
	
	@Test
	public void testSmallOne() {
		this.reader.setInputFilePath(INPUT_FILE_PATH_SMALL_ONE);
		test(ANSWER_SMALL_ONE);
	}
	
	@Test
	public void testSmallTwo() {
		this.reader.setInputFilePath(INPUT_FILE_PATH_SMALL_TWO);
		test(ANSWER_SMALL_TWO);
	}

}