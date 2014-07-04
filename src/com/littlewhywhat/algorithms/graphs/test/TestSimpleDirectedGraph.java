package com.littlewhywhat.algorithms.graphs.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Id;
import com.littlewhywhat.algorithms.graphs.SimpleDirectedGraph;

public class TestSimpleDirectedGraph {

	private class Item implements Id {

		private int id;

		private Item(int id) {
			this.id = id;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public String toString() {
			return String.valueOf(id);
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Item)) {
				return false;
			}
			Item other = (Item) obj;
			if (id != other.id) {
				return false;
			}
			return true;
		}


	}

	private Graph<Item> graph;
	private final Item[] items = new Item[] { new Item(0), new Item(1),
			new Item(2), new Item(3) };
	private Random random = new Random();

	@Before
	public void setUp() throws Exception {
		graph = new SimpleDirectedGraph<Item>();
	}

	@Test
	public void testReverse() {
		Assert.fail("Not implemented");
	}

	@Test
	public void testSize() {
		int count = 0;
		Assert.assertEquals(count, graph.size());
		for (Item item : items) {
			graph.add(item);
			Assert.assertEquals(++count, graph.size());
		}
		for (Item item : items) {
			graph.remove(item.getId());
			Assert.assertEquals(--count, graph.size());
		}
	}

	@Test
	public void testRemove() {
		Assert.fail("Not implemented");
		addAllItems();
		final int index = 0;
		final int id = items[index].getId();
		graph.remove(id);
		Assert.assertEquals(null, graph.get(id));
		Assert.assertEquals(items.length - 1, graph.size());
	}

	private void addAllItems() {
		for (Item item : items) {
			graph.add(item);
		}
	}

	@Test
	public void testIterator() {
		Assert.fail("Not implemented");
	}

	@Test
	public void testIsEmpty() {
		final Item item = items[0];
		Assert.assertEquals(true, graph.isEmpty());
		graph.add(item);
		Assert.assertEquals(false, graph.isEmpty());
		graph.remove(item.getId());
		Assert.assertEquals(true, graph.isEmpty());
	}

	@Test
	public void testContains() {
		final Item item = getRandomItem();
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
		final List<Item> list = new ArrayList<Item>();
		for (Item item : items)
			list.add(item);
		Assert.assertEquals(true, graph.addAll(list));
		Assert.assertEquals(list.size(), graph.size());
		Assert.assertEquals(false, graph.addAll(list));
	}

	@Test
	public void testAdd() {
		for (Item item : items)
			Assert.assertEquals(true, graph.add(item));
		Assert.assertEquals(items.length, graph.size());
		for (Item item : items)
			Assert.assertEquals(false, graph.add(item));
		Assert.assertEquals(items.length, graph.size());
		for (Item item : items)
			Assert.assertEquals(true, graph.contains(item.getId()));
	}

	private Item getRandomItem() {
		return items[random.nextInt(items.length)];
	}

	@Test
	public void testGet() {
		addAllItems();
		for (Item item : items)
			Assert.assertEquals(item, graph.get(item.getId()));
	}

	@Test
	public void testGetConnections() {
		Assert.fail("Not implemented");
	}

	@Test
	public void testConnect() {
		Assert.fail("Not implemented");
	}
}
