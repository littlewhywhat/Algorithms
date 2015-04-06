package com.littlewhywhat.algorithms.graphs.dijsktra.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.Algorithm;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraAlgo;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraphReader;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;

public class TestDijkstraAlgo {

<<<<<<< HEAD:src/com/littlewhywhat/algorithms/graphs/dijsktra/test/TestDijkstraAlgo.java
	private static final String FOLDER = "src/com/littlewhywhat/algorithms/graphs/dijsktra/test/input/";
=======
	private static final String FOLDER = "algorithms/graphs/dijkstra/";
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/graphs/dijsktra/TestDijkstraAlgo.java
	private static final String INPUT_FILE_PATH_SMALL = FOLDER + "dijkstraDataSmall.txt";
	private static final String INPUT_FILE_PATH = FOLDER + "dijkstraData.txt";
	private static final int[] ANSWERS_BIG = new int[] { 2599, 2610, 2947,
			2052, 2367, 2399, 2029, 2442, 2505, 3068 };
	private static final int[] ANSWERS_SMALL = new int[] { 0, 7, 7, 5 };
	private GraphReader reader = new DijkstraGraphReader();
	private Algorithm<Void, DijkstraGraph, int[]> algo = new DijkstraAlgo();

	private int[] indices = new int[] { 6, 36, 58, 81, 98, 114, 132, 164, 187,
			196 };

	@Before
	public void setUp() throws Exception {
		reader.setInputFilePath(INPUT_FILE_PATH);
	}

	@Test
	public void testBig() {
		reader.setInputFilePath(INPUT_FILE_PATH);
		reader.setGraph(new DijkstraGraph(1));
		executeAlgo();
		int[] answers = new int[indices.length];
		for (int i = 0; i < answers.length; i++) {
			answers[i] = algo.getOutput()[indices[i]];
		}
		Assert.assertArrayEquals(ANSWERS_BIG, answers);
	}

	@Test
	public void testSmall() {
		reader.setInputFilePath(INPUT_FILE_PATH_SMALL);
		reader.setGraph(new DijkstraGraph(1));
		executeAlgo();
		Assert.assertArrayEquals(ANSWERS_SMALL, algo.getOutput());
	}

	private void executeAlgo() {
		reader.read();
		algo.setData((DijkstraGraph) reader.getData());
		algo.execute();
	}

}
