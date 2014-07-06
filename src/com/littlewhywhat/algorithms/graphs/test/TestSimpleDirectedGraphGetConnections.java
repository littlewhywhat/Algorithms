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
		for (TestItem item : items)
			graph.add(item);
		connectFirstWithAll();
	}
	
	private void connectFirstWithAll() {
		for (TestItem item : items) {
			graph.connect(first, item);
		}
	}
	
	private ListIterator<TestItem> getConnectionsOfFirst() {
		return graph.getConnections(first);
	}
	
	private void getToEnd(ListIterator<TestItem> iterator) {
		while (iterator.hasNext())
			iterator.next();		
	}

	@Test
	public void testAdd() {
		final ListIterator<TestItem> iterator = getConnectionsOfFirst();
		for (TestItem item : items) {
			iterator.add(item);
			iterator.next();
		}
		while (iterator.hasPrevious())
			iterator.previous();
		for (TestItem item : items) {
			Assert.assertEquals(item, iterator.next());
			Assert.assertEquals(item, iterator.next());
		}
	}

	@Test
	public void testHasNextAndIndex() {
		final ListIterator<TestItem> iterator = getConnectionsOfFirst();
		int count = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(count, iterator.nextIndex());
			iterator.next();
			count++;
		}
		Assert.assertEquals(items.length, iterator.nextIndex());
		Assert.assertEquals(items.length, count);
	}

	@Test
	public void testHasPreviousAndIndex() {
		final ListIterator<TestItem> iterator = getConnectionsOfFirst();
		getToEnd(iterator);
		int count = items.length - 1;
		while (iterator.hasPrevious()) {
			Assert.assertEquals(count, iterator.previousIndex());
			iterator.previous();
			count--;
		}
		Assert.assertEquals(-1, iterator.previousIndex());
		Assert.assertEquals(-1, count);
	}

	@Test
	public void testNext() {
		final ListIterator<TestItem> iterator = getConnectionsOfFirst();
		for (TestItem item : items)
			Assert.assertEquals(item, iterator.next());
	}

	@Test
	public void testPrevious() {
		final ListIterator<TestItem> iterator = getConnectionsOfFirst();
		getToEnd(iterator);
		for (int i = items.length - 1; i > -1; i--) {
			Assert.assertEquals(items[i], iterator.previous());
		}
	}

	@Test
	public void testRemove() {
		ListIterator<TestItem> iterator = getConnectionsOfFirst();
		Assert.assertEquals(false, canRemoveFrom(iterator));
		while (iterator.hasNext()) {
			iterator.next();
			Assert.assertEquals(true, iterator.hasPrevious());
			iterator.remove();
			Assert.assertEquals(false, canRemoveFrom(iterator));
			Assert.assertEquals(false, iterator.hasPrevious());
		}
		connectFirstWithAll();
		iterator = getConnectionsOfFirst();
		getToEnd(iterator);
		while (iterator.hasPrevious()) {
			iterator.previous();
			Assert.assertEquals(true, iterator.hasNext());
			iterator.remove();
			Assert.assertEquals(false, canRemoveFrom(iterator));
			Assert.assertEquals(false, iterator.hasNext());
		}
	}

	private boolean canRemoveFrom(ListIterator<TestItem> iterator) {
		try {
			iterator.remove();
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}
	
	private boolean canSet(ListIterator<TestItem> iterator) {
		try {
			iterator.set(first);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	@Test
	public void testSet() {
		ListIterator<TestItem> iterator = getConnectionsOfFirst();
		Assert.assertEquals(false, canSet(iterator));
		iterator.next();
		while (iterator.hasNext()) {
			Assert.assertNotEquals(first, iterator.next());
			iterator.set(first);
			iterator.remove();
			iterator.add(first);
			Assert.assertEquals(false, canSet(iterator));
			Assert.assertEquals(first, iterator.previous());
			iterator.next();
		}
		while (iterator.hasPrevious()) {
			iterator.remove();
			iterator.previous();
		}
		iterator.remove();
		connectFirstWithAll();
		iterator = getConnectionsOfFirst();
		getToEnd(iterator);
		while (iterator.previousIndex()!= 0) {
			TestItem item = iterator.previous();
			Assert.assertNotEquals(first, item);
			iterator.set(first);
			iterator.remove();
			Assert.assertEquals(false, canSet(iterator));
			iterator.add(first);
			iterator.previous();
		}
		
	}
}
