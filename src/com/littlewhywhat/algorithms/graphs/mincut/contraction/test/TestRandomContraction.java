package com.littlewhywhat.algorithms.graphs.mincut.contraction.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.mincut.contraction.GraphReader;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.RandomContraction;

public class TestRandomContraction {

	public static final String FOLDER = "src/com/littlewhywhat/algorithms/graphs/mincut/contraction/test/input/";
	private static final String INPUT_FILE_PATH_SMALL = FOLDER + "SmallGraph";
	private static final String INPUT_FILE_PATH_BIG = FOLDER + "kargerMinCut.txt";
	private static final int ANSWER_SMALL = 2;
	private static final int ANSWER_BIG = 17;
	private RandomContraction randomContraction;
	private GraphReader reader;
	
	@Before
	public void setUp() throws Exception {
		this.randomContraction = new RandomContraction();
		this.reader = new GraphReader();
		
	}

	@Test
	public void testBig() {
		this.reader.setInputFilePath(INPUT_FILE_PATH_BIG);
		test(ANSWER_BIG);
	}

	@Test
	public void testSmall() {
		this.reader.setInputFilePath(INPUT_FILE_PATH_SMALL);
		test(ANSWER_SMALL);
	}
	
	private void test(int answer) {
		int min = 999999999;
		for (int i = 0; i < 300; i++) {
			reader.read();
			randomContraction.setData(reader.getData());
			randomContraction.execute();
			if (randomContraction.getOutput() < min)
				min = randomContraction.getOutput();
		}
		Assert.assertEquals(answer, min);		
	}

}
