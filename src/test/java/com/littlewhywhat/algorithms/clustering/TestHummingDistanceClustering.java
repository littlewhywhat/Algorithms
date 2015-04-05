package com.littlewhywhat.algorithms.clustering.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.clustering.ClusteringReader;
import com.littlewhywhat.algorithms.clustering.HummingDistanceClustering;

public class TestHummingDistanceClustering {

	private static final String FOLDER = "algorithms/clustering/";
	private static final String INPUT_BIG = FOLDER + "clustering_big.txt";
	private static final String INPUT_SMALL = FOLDER + "clustering_small.txt";
	private static final int ANSWER_BIG = 6118;
	private static final int ANSWER_SMALL = 1;
	HummingDistanceClustering algo;
	ClusteringReader reader;
	
	
	@Before
	public void setUp() throws Exception {
		reader = new ClusteringReader();
		algo = new HummingDistanceClustering();
	}

	// @Test
	// public void testBig() {
	// 	test(INPUT_BIG, ANSWER_BIG);
	// }
	
	@Test
	public void testSmall() {
		test(INPUT_SMALL, ANSWER_SMALL);
	}
	
	private void test(String input, int answer) {
		reader.setInputFilePath(input);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		Assert.assertEquals(answer, algo.getOutput().intValue());
	}

}
