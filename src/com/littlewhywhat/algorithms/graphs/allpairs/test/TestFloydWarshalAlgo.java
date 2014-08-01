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
	public void test() {
		reader.setGraph(graph);
		reader.setInputFilePath(INPUT_ONE);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		System.out.print(algo.getOutput());
	}

}
