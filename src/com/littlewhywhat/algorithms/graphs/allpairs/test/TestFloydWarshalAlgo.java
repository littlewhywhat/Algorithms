package com.littlewhywhat.algorithms.graphs.allpairs.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.allpairs.FloydWarshalAlgo;
import com.littlewhywhat.algorithms.graphs.clustering.ClusteringReader;
import com.littlewhywhat.algorithms.graphs.clustering.UnionFindGraph;

public class TestFloydWarshalAlgo {
	private static final String FOLDER = "src/com/littlewhywhat/algorithms/graphs/allpairs/test/input/";
	private static final String INPUT_ONE = FOLDER + "graph1.txt";
	private static final String INPUT_TWO = FOLDER + "graph2.txt";
	private static final String INPUT_THREE = FOLDER + "graph3.txt";
	private static final String INPUT_SMALL = FOLDER + "graph.txt";
	private FloydWarshalAlgo algo;
	private ClusteringReader reader;
	private Graph graph;
	@Before
	public void setUp() throws Exception {
		algo = new FloydWarshalAlgo();
		reader = new ClusteringReader();
		graph = new UnionFindGraph();
		
	}

	@Test
	public void testOne() {
		test(INPUT_ONE);
	}
	
	@Test
	public void testTwo() {
		test(INPUT_TWO);
	}
	
	@Test
	public void testThree() {
		test(INPUT_THREE);
		int[][] output = algo.getOutput();
		int min = Integer.MAX_VALUE;
		for (int[] line : output) 
			for (int answer : line)
				if (answer < min)
					min = answer;
		System.out.println(min);
	}
	
	@Test
	public void testSmall() {
		test(INPUT_SMALL);
	}

	private void test(String inputOne) {
		reader.setGraph(graph);
		reader.setInputFilePath(inputOne);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		System.out.print(algo.getOutput());
	}

}
