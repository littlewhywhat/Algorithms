package com.littlewhywhat.algorithms.graphs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.SimpleDirectedGraph;

public class TestSimpleDirectedGraphGetConnections {

	private Graph<TestItem> graph;
	private final TestItem[] items = TestItem.getNewTestArray();
	
	@Before
	public void setUp() throws Exception {
		graph = new SimpleDirectedGraph<TestItem>();
		
	}

	@Test
	public void testAdd() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testHasNext() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void testHasPrevious() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testNext() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testNextIndex() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testPrevious() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testPreviousIndex() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testRemove() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testSet() {
		Assert.fail("Not yet implemented");
	}
}
