package com.littlewhywhat.algorithms.twosat.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.twosat.ClauseCleaner;
import com.littlewhywhat.algorithms.twosat.ClauseReader;
import com.littlewhywhat.algorithms.twosat.PapadimitriouAlgo;

public class PapadimitriouAlgoTest {
	
	private static final String FOLDER = "src/com/littlewhywhat/algorithms/twosat/test/input/";
	private static final String INPUT_TEST = FOLDER + "test.txt";
	private static final String INPUT_SMALL = FOLDER + "2sat0.txt";
	private static final String INPUT = FOLDER + "2sat0.txt";
	private PapadimitriouAlgo algo;
	private ClauseCleaner cleaner;
	private ClauseReader reader;

	@Before
	public void setUp() throws Exception {
		algo = new PapadimitriouAlgo();
		reader = new ClauseReader();
		cleaner = new ClauseCleaner();
	}

	public void test(String inputFilePath, boolean output) {
		reader.setInputFilePath(inputFilePath);
		reader.read();
		System.out.println(reader.getData().size());
		cleaner.setConfig(reader.getConfig());
		cleaner.setData(reader.getData());
		cleaner.execute();
		System.out.println(cleaner.getOutput().size());
		algo.setConfig(reader.getConfig());
		algo.setData(cleaner.getData());
		algo.execute();
		Assert.assertEquals(output, algo.getOutput());
	}

	@Test
	public void testSmall() {
		for (int i = 0; i < 10; i++)
			test(INPUT_SMALL, true);
	}
	
	@Test
	public void testTest() {
		test(INPUT_TEST, false);
	}

}
