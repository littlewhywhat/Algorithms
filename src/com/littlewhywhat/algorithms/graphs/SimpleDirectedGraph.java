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
		return vertices.get(id).item;
	}

	@Override
	public boolean add(I item) {
		if (vertices.containsKey(item.getId()))
			return false;
		vertices.put(item.getId(), new Vertice(item));
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends I> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		vertices.clear();
	}

	@Override
	public boolean contains(Object id) {
		if (id instanceof Integer)
			return vertices.containsKey(id);
		return false;
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
		if (id instanceof Integer)
			return vertices.remove(id) != null;
		return false;
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
