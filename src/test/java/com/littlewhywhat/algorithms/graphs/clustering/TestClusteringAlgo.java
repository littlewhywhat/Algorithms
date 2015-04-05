package com.littlewhywhat.algorithms.graphs.clustering.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.clustering.ClusteringAlgo;
import com.littlewhywhat.algorithms.graphs.clustering.ClusteringReader;
import com.littlewhywhat.algorithms.graphs.clustering.UnionFindGraph;

public class TestClusteringAlgo {

	private static final String FOLDER = "algorithms/graphs/clustering/";
	private static final String INPUT_SMALL = FOLDER + "clustering_small.txt";
	private static final String INPUT_BIG = FOLDER + "clustering_big.txt";
	private static final int CONFIG_SMALL = 2;
	private static final int ANSWER_SMALL = 5;
	private static final int ANSWER_BIG = 106;
	private static final int CONFIG_BIG = 4;
	private ClusteringReader reader;
	private UnionFindGraph graph;
	private ClusteringAlgo algo;

	@Before
	public void setUp() throws Exception {
		reader = new ClusteringReader();
		graph = new UnionFindGraph();
		algo = new ClusteringAlgo();
	}

	@Test
	public void testSmall() {
		test(INPUT_SMALL, CONFIG_SMALL, ANSWER_SMALL);
	}
	
	@Test 
	public void testBig() {
		test(INPUT_BIG, CONFIG_BIG, ANSWER_BIG);
	}
	
	public void test(String inputPath, int config, int answer) {
		reader.setInputFilePath(inputPath);
		reader.setGraph(graph);
		reader.read();
		algo.setConfig(config);
		algo.setData((UnionFindGraph) reader.getData());
		algo.execute();
		Assert.assertEquals(answer, algo.getOutput().intValue());
	}

}
