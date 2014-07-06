package com.littlewhywhat.algorithms.graphs.test;

import java.util.ListIterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.SimpleDirectedGraph;

public class TestSimpleDirectedGraphGetConnections {

	private Graph<TestItem> graph;
	private final TestItem[] items = TestItem.getNewTestArray();
	private final TestItem first = items[0];
	
	@Before
	public void setUp() throws Exception {
		graph = new SimpleDirectedGraph<TestItem>();		
		for (TestItem item : items) {
			graph.add(item);
			graph.connect(first.getId(), item.getId());
		}
	}

	private ListIterator<TestItem> getConnectionsOfFirst() {
		return graph.getConnections(first);
	}
	
	@Test
	public void testAdd() {
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void testHasNext() {
		final ListIterator<TestItem> iterator = getConnectionsOfFirst();
		int count = 0;
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		Assert.assertEquals(items.length, count);
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
