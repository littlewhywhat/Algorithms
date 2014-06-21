package com.littlewhywhat.algorithms.medianmaintenance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.Algorithm;
import com.littlewhywhat.algorithms.io.txt.FileInputReader;
import com.littlewhywhat.algorithms.sort.SortingReader;

public class TestMMAlgo {

	private static final String FOLDER = "src/com/littlewhywhat/algorithms/medianmaintenance/test/input/";
	private static final String INPUT_BIG = FOLDER + "Median.txt";
	private static final String INPUT_SMALL = FOLDER + "MedianSmall.txt";
	private FileInputReader<Void, int[]> reader;
	private Algorithm<Void, int[], Integer> algo;

	@Before
	public void setUp() throws Exception {
		reader = new SortingReader();
		algo = new MMAlgo();
	}

	@Test
	public void testBig() {
		test(INPUT_SMALL, 0);
	}

	@Test
	public void testSmall() {
		test(INPUT_BIG, 0);
	}

	public void test(String inputFilePath, int answer) {
		reader.setInputFilePath(inputFilePath);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		Assert.assertEquals(answer, algo.getOutput().intValue());
	}

}
