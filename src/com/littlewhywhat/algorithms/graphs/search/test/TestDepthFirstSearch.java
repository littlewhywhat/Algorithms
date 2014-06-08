package com.littlewhywhat.algorithms.graphs.search.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.search.DepthFirstSearch;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.Vertice;
import com.littlewhywhat.algorithms.graphs.search.SearchGraphReader;

public class TestDepthFirstSearch {

	private static final String FOLDER = "src/com/littlewhywhat/algorithms/graphs/csc/test/input/";
	private static final String INPUT_GRAPH = FOLDER + "SCCsmall.txt";
	private static final String INPUT_GRAPH_BIG = FOLDER + "SCC.txt";
	private SearchGraphReader reader;
	private DepthFirstSearch search;
	
	@Before
	public void setUp() throws Exception {
		reader = new SearchGraphReader();
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
		SearchGraph graph = reader.getData();
		for (Vertice vertice : graph) {
			Assert.assertEquals(false, vertice.isExplored());
		}
		search.setData(reader.getData());
		search.execute();
		for (Vertice vertice : graph) {
			Assert.assertEquals(true, vertice.isExplored());
		}
	}

}
