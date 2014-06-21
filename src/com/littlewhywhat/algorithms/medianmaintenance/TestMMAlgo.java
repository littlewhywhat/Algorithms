package com.littlewhywhat.algorithms.medianmaintenance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.Algorithm;
import com.littlewhywhat.algorithms.io.txt.FileInputReader;
import com.littlewhywhat.algorithms.sort.SortingReader;

public class TestMMAlgo {

	private final String FOLDER = "src/com/littlewhywhat/algorithms/medianmaintenance/test/input/";
	private final String INPUT_BIG = FOLDER + "Median.txt";
	private final FileInputReader<Void, int[]> reader = new SortingReader();
	private final Algorithm<Void, int[], Integer> algo = new MMAlgo();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		reader.setInputFilePath(INPUT_BIG);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		Assert.assertEquals(null, algo.getOutput());
	}

}
