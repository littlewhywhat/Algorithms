package com.littlewhywhat.algorithms.graphs.csc.test;


import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.csc.KosarajuAlgo;
import com.littlewhywhat.algorithms.graphs.csc.ReversibleGraph;
import com.littlewhywhat.algorithms.graphs.csc.ReversibleGraphReader;
import com.littlewhywhat.algorithms.graphs.search.DepthFirstSearch;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.Vertice;
import com.littlewhywhat.algorithms.graphs.search.test.TestDepthFirstSearch;

public class TestKosaraju {

	private KosarajuAlgo algo;
	private DepthFirstSearch search;
	private ReversibleGraphReader reader;
	
	@Before
	public void setUp() throws Exception {
		reader = new ReversibleGraphReader();
		algo = new KosarajuAlgo();
	}

	@Test
	public void test() {
		reader.setInputFilePath(TestDepthFirstSearch.INPUT_GRAPH_BIG);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		Collections.sort(algo.getOutput());
		for (int i = 0; i < 5; i++)
			System.out.println(algo.getOutput().pollLast());
	}
	
	public void test1() {
		reader.setInputFilePath(TestDepthFirstSearch.INPUT_GRAPH);
		reader.read();
		ReversibleGraph graph = reader.getData();
		search = new DepthFirstSearch();
		search.setData(graph.getGraph());
		search.execute();
		for (Vertice vertice : graph.getGraph())
			Assert.assertEquals(true, vertice.isExplored());
		System.out.println();
		search.setData(graph.getReversed());
		search.execute();
		for (Vertice vertice : graph.getReversed())
			Assert.assertEquals(true, vertice.isExplored());
		
	}

}
