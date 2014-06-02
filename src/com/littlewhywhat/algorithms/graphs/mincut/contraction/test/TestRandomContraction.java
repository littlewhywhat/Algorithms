package com.littlewhywhat.algorithms.graphs.mincut.contraction.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.mincut.contraction.GraphReader;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.RandomContraction;

public class TestRandomContraction {

	public static final String FOLDER = "src/com/littlewhywhat/algorithms/graphs/mincut/contraction/test/input/";
	private static final String INPUT_FILE_PATH_SMALL = FOLDER + "SmallGraph";
	private static final String INPUT_FILE_PATH_BIG = FOLDER + "kargerMinCut.txt";
	private RandomContraction randomContraction;
	private GraphReader reader;
	
	@Before
	public void setUp() throws Exception {
		this.randomContraction = new RandomContraction();
		this.reader = new GraphReader();
		this.reader.setInputFilePath(INPUT_FILE_PATH_BIG);
	}

	@Test
	public void test() {
		int min = 999999999;
		for (int i = 0; i < 100; i++) {
			reader.read();
			randomContraction.setData(reader.getData());
			randomContraction.execute();
			if (randomContraction.getOutput() < min)
				min = randomContraction.getOutput();
		}
		System.out.println(min);
	}

}
