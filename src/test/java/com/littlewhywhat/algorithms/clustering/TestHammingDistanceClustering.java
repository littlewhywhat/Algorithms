package com.littlewhywhat.algorithms.clustering;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import com.littlewhywhat.algorithms.Algorithm;
import com.littlewhywhat.algorithms.io.txt.FileInputReader;

public class TestHammingDistanceClustering {

	private static final String FOLDER = "algorithms/clustering/";
	private static final String INPUT_BIG = FOLDER + "clustering_big.txt";
	private static final String INPUT_SMALL = FOLDER + "clustering_small.txt";
	private static final int ANSWER_BIG = 6118;
	private static final int ANSWER_SMALL = 1;
	private Algorithm<Integer, Map<HammingDistance, Boolean>, Integer> algo;
	private FileInputReader<Integer, Map<HammingDistance, Boolean>> reader;
	
	@Before
	public void setUp() throws Exception {
		reader = new ClusteringReader();
		algo = new HammingDistanceClustering();
	}

	@Test
	public void testBig() {
		test(INPUT_BIG, ANSWER_BIG);
	}
	
	@Test
	public void testSmall() {
		test(INPUT_SMALL, ANSWER_SMALL);
	}
	
	private void test(String input, int answer) {
		reader.setInputFilePath(input);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		assertEquals(answer, algo.getOutput().intValue());
	}

}
