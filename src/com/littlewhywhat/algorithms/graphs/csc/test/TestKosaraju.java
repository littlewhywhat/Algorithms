package com.littlewhywhat.algorithms.graphs.csc.test;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.csc.KosarajuAlgo;
import com.littlewhywhat.algorithms.graphs.csc.KosarajuGraphsReader;
import com.littlewhywhat.algorithms.graphs.search.test.TestDepthFirstSearch;

public class TestKosaraju {

	private KosarajuAlgo algo;
	private KosarajuGraphsReader reader;
	private final int[] ANSWERS_BIG = new int[] { 434821, 968, 459, 313, 211 };
	private final int[] ANSWERS_SMALL = new int[] { 3, 3, 3 };

	@Before
	public void setUp() throws Exception {
		reader = new KosarajuGraphsReader();
		algo = new KosarajuAlgo();
	}

	@Test
	public void testSmall() {
		test(TestDepthFirstSearch.INPUT_GRAPH, ANSWERS_SMALL);
	}
	
	@Test
	public void testBig() {
		test(TestDepthFirstSearch.INPUT_GRAPH_BIG, ANSWERS_BIG);
	}
	
	public void test(String inputFilePath, int[] answers) {
		reader.setInputFilePath(inputFilePath);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		Collections.sort(algo.getOutput());
		for (int i = 0; i < answers.length; i++)
			Assert.assertEquals(answers[i], algo.getOutput().pollLast().intValue());
	}

}
