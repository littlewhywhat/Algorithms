package com.littlewhywhat.algorithms.medianmaintenance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.Algorithm;
import com.littlewhywhat.algorithms.io.txt.FileInputReader;
import com.littlewhywhat.algorithms.sort.SortingReader;

public class TestMMAlgo {

<<<<<<< HEAD:src/com/littlewhywhat/algorithms/medianmaintenance/TestMMAlgo.java
	private static final String FOLDER = "src/com/littlewhywhat/algorithms/medianmaintenance/test/input/";
=======
	private static final String FOLDER = "algorithms/medianmaintenance/";
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/medianmaintenance/TestMMAlgo.java
	private static final String INPUT_BIG = FOLDER + "Median.txt";
	private static final String INPUT_SMALL = FOLDER + "MedianSmall.txt";
	private static final int ANSWER_BIG = 1213;
	private static final int ANSWER_SMALL = 30;
	private FileInputReader<Void, int[]> reader;
	private Algorithm<Void, int[], Integer> algo;

	@Before
	public void setUp() throws Exception {
		reader = new SortingReader();
		algo = new MMAlgo();
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
		algo.setData(reader.getData());
		algo.execute();
		Assert.assertEquals(answer, algo.getOutput().intValue());
	}

}
