package com.littlewhywhat.algorithms.graphs.search.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;
import com.littlewhywhat.algorithms.graphs.search.DepthFirstSearch;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.SearchVertice;
import com.littlewhywhat.algorithms.graphs.search.SearchGraphReader;

public class TestDepthFirstSearch {

	private static final String FOLDER = "src/com/littlewhywhat/algorithms/graphs/csc/test/input/";
	public static final String INPUT_GRAPH = FOLDER + "SCCsmall.txt";
	public static final String INPUT_GRAPH_BIG = FOLDER + "SCC.txt";
	private GraphReader reader;
	private DepthFirstSearch search;

	@Before
	public void setUp() throws Exception {
		reader = new SearchGraphReader();
		reader.setGraph(new SearchGraph());
		search = new DepthFirstSearch();
	}

	@Test
	public void testSmall() {
		reader.setInputFilePath(INPUT_GRAPH);
		test();
	}

	@Test
	public void testBig() {
		reader.setInputFilePath(INPUT_GRAPH_BIG);
		test();
	}

	public void test() {
		reader.read();
		Graph graph = reader.getData();
		testIsExplored(graph, false);
		search.setData((SearchGraph) reader.getData());
		search.execute();
		testIsExplored(graph, true);
		((SearchGraph) graph).reset();
		testIsExplored(graph, false);
	}

	public void testIsExplored(Graph graph, boolean value) {
		int i = 0;
		for (Vertice vertice : graph) {
			i++;
			Assert.assertEquals(value, ((SearchVertice)vertice).isExplored());
		}
		Assert.assertEquals(graph.size(), i);
	}
}
