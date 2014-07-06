package com.littlewhywhat.algorithms.graphs.test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.SimpleDirectedGraph;

public class TestSimpleDirectedGraph {

	private Graph<TestItem> graph;
	private final TestItem[] items = TestItem.getNewTestArray();
	private Random random = new Random();

	@Before
	public void setUp() throws Exception {
		graph = new SimpleDirectedGraph<TestItem>();
	}

	@Test
	public void testReverse() {
		final TestItem one = items[0];
		final TestItem two = items[1];
		addAllItems();
		graph.connect(one.getId(), two.getId());
		Assert.assertEquals(true, graph.getConnections(one).hasNext());
		Assert.assertEquals(false, graph.getConnections(two).hasNext());
		((SimpleDirectedGraph<TestItem>) graph).reverse();
		Assert.assertEquals(false, graph.getConnections(one).hasNext());
		Assert.assertEquals(true, graph.getConnections(two).hasNext());
		
	}

	@Test
	public void testSize() {
		int count = 0;
		Assert.assertEquals(count, graph.size());
		for (TestItem item : items) {
			graph.add(item);
			Assert.assertEquals(++count, graph.size());
		}
		for (TestItem item : items) {
			graph.remove(item.getId());
			Assert.assertEquals(--count, graph.size());
		}
	}

	@Test
	public void testRemove() {
		final TestItem one = items[0];
		final TestItem two = items[1];
		addAllItems();
		graph.connect(two.getId(), one.getId());
		Assert.assertEquals(true, graph.remove(one.getId()));
		Assert.assertEquals(null, graph.get(one.getId()));
		Assert.assertEquals(false, graph.getConnections(two).hasNext());
		Assert.assertEquals(items.length - 1, graph.size());
	}

	private void addAllItems() {
		for (TestItem item : items) {
			graph.add(item);
		}
	}

	@Test
	public void testIterator() {
		Assert.fail("Not implemented");
	}

	@Test
	public void testIsEmpty() {
		final TestItem item = items[0];
		Assert.assertEquals(true, graph.isEmpty());
		graph.add(item);
		Assert.assertEquals(false, graph.isEmpty());
		graph.remove(item.getId());
		Assert.assertEquals(true, graph.isEmpty());
	}

	@Test
	public void testContains() {
		final TestItem item = getRandomItem();
		addAllItems();
		Assert.assertEquals(true, graph.contains(item.getId()));
		graph.remove(item.getId());
		Assert.assertEquals(false, graph.contains(item.getId()));
	}

	@Test
	public void testClear() {
		Assert.assertEquals(true, graph.isEmpty());
		addAllItems();
		Assert.assertEquals(false, graph.isEmpty());
		graph.clear();
		Assert.assertEquals(true, graph.isEmpty());
	}

	@Test
	public void testAddAll() {
		final List<TestItem> list = new ArrayList<TestItem>();
		for (TestItem item : items)
			list.add(item);
		Assert.assertEquals(true, graph.addAll(list));
		Assert.assertEquals(list.size(), graph.size());
		Assert.assertEquals(false, graph.addAll(list));
	}

	@Test
	public void testAdd() {
		for (TestItem item : items)
			Assert.assertEquals(true, graph.add(item));
		Assert.assertEquals(items.length, graph.size());
		for (TestItem item : items)
			Assert.assertEquals(false, graph.add(item));
		Assert.assertEquals(items.length, graph.size());
		for (TestItem item : items)
			Assert.assertEquals(true, graph.contains(item.getId()));
	}

	private TestItem getRandomItem() {
		return items[random.nextInt(items.length)];
	}

	@Test
	public void testGet() {
		addAllItems();
		for (TestItem item : items)
			Assert.assertEquals(item, graph.get(item.getId()));
	}

	@Test
	public void testConnect() {
		final TestItem one = items[0];
		final TestItem two = items[1];
		final TestItem three = items[2];
		graph.add(one);
		graph.add(two);
		graph.connect(one.getId(), two.getId());
		try {
			graph.connect(one.getId(), three.getId());
			Assert.fail();
		} catch (IllegalArgumentException e) {}
		final ListIterator<TestItem> iterator = graph.getConnections(one);
		int count = 0;
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		Assert.assertEquals(1, count );
	}
}
