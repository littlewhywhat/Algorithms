package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ConnectionsList implements Iterable<Vertice>, Collection<Vertice> {

	private final List<Vertice> list;
	
	public ConnectionsList(List<Vertice> list) {
		this.list = list;
	}
	
	@Override
	public boolean add(Vertice vertice) {
		return list.add(vertice);
	}

	@Override
	public boolean addAll(Collection<? extends Vertice> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		this.list.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return this.list.size();
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
	public Iterator<Vertice> iterator() {
		return this.list.iterator();
	}

}
