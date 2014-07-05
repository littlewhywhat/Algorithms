package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

public class SimpleDirectedGraph<I extends Id> implements DirectedGraph<I> {

	private class Vertice {
		private I item;

		private Vertice(I item) {
			this.item = item;
		}
	}

	private final Map<Integer, Vertice> vertices = new HashMap<Integer, Vertice>();

	@Override
	public void connect(int one, int two) {
		// TODO Auto-generated method stub

	}

	@Override
	public ListIterator<I> getConnections(I item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public I get(int id) {
		Vertice vertice = vertices.get(id);
		if (vertice != null)
			return vertice.item;
		return null;
	}

	@Override
	public boolean add(I item) {
		return vertices.put(item.getId(), new Vertice(item)) == null;
	}

	@Override
	public boolean addAll(Collection<? extends I> c) {
		boolean result = false;
		for (I item : c)
			if (add(item))
				result = true;
		return result;
	}

	@Override
	public void clear() {
		vertices.clear();
	}

	@Override
	public boolean contains(Object id) {
		return vertices.containsKey(id);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public Iterator<I> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object id) {
		// TODO Auto-generated method stub
		return vertices.remove(id) != null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return vertices.size();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void reverse() {
		// TODO Auto-generated method stub

	}

}
