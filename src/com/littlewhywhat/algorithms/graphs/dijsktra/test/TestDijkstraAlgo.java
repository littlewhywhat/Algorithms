package com.littlewhywhat.algorithms.graphs.dijsktra.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraAlgo;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraphFiller;
import com.littlewhywhat.algorithms.graphs.io.GraphFiller;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;
import com.littlewhywhat.algorithms.graphs.io.SimpleSizeCounter;
import com.littlewhywhat.algorithms.graphs.io.SizeCounter;

public class TestDijkstraAlgo {
	
	private static final String INPUT_FILE_PATH_SMALL = "src/com/littlewhywhat/algorithms/graphs/dijsktra/test/input/dijkstraDataSmall.txt";
	private GraphReader reader = new GraphReader();
	private Graph graph = new DijkstraGraph(0);
	private GraphFiller filler = new DijkstraGraphFiller();
	private SizeCounter sizeCounter = new SimpleSizeCounter();
	private DijkstraAlgo algo = new DijkstraAlgo();

	@Before
	public void setUp() throws Exception {
		reader.setGraph(graph);
		reader.setGraphFiller(filler);
		reader.setSizeCounter(sizeCounter );
		reader.setInputFilePath(INPUT_FILE_PATH_SMALL);
	}

	@Test
	public void test() {
		reader.read();
	}

}
