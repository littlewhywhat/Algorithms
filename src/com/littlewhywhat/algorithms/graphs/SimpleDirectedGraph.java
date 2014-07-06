package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

public class SimpleDirectedGraph<I extends Id> implements DirectedGraph<I> {

	private class VerticesIterator implements Iterator<I> {

		private final Iterator<Vertice> iterator;

		private VerticesIterator(Iterator<Vertice> iterator) {
			this.iterator = iterator;
		}
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public I next() {
			return iterator.next().item;
		}

		@Override
		public void remove() {
			iterator.remove();
		}

	}

	private class ConnectionsIterator implements ListIterator<I> {

		private final ListIterator<Edge> iterator;
		private final Vertice vertice;
		private Edge currentState;

		private ConnectionsIterator(Vertice vertice) {
			this.iterator = vertice.out.listIterator();
			this.vertice = vertice;
		}

		private I getConnection(Edge edge) {
			return edge.end.item;
		}

		@Override
		public void add(I item) {
			Edge edge = getNewEdge(vertice.item, item);
			if (edge != null) {
				vertice.in.add(edge);
				iterator.add(edge);
				currentState = null;
			} else
				throw new IllegalArgumentException();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public boolean hasPrevious() {
			return iterator.hasPrevious();
		}

		@Override
		public I next() {
			return getConnection(setAndGetCurrentState(iterator.next()));
		}

		private Edge setAndGetCurrentState(Edge state) {
			return this.currentState = state;
		}

		@Override
		public int nextIndex() {
			return iterator.nextIndex();
		}

		@Override
		public I previous() {
			return getConnection(setAndGetCurrentState(iterator.previous()));
		}

		@Override
		public int previousIndex() {
			return iterator.previousIndex();
		}

		@Override
		public void remove() {
			if (stateIsLegal()) {
				iterator.remove();
				currentState.end.in.remove(currentState);
				currentState = null;
			}
		}

		private boolean stateIsLegal() {
			if (currentState == null)
				throw new IllegalStateException();
			return true;
		}

		@Override
		public void set(I item) {
			if (stateIsLegal())
				currentState.setEnd(vertices.get(item.getId()));
		}

	}

	private class Vertice {
		private final I item;
		private final LinkedList<Edge> in = new LinkedList<Edge>();
		private final LinkedList<Edge> out = new LinkedList<Edge>();

		private Vertice(I item) {
			this.item = item;
		}

		@Override
		public String toString() {
			return item.toString();
		}
	}

	private class Edge {
		private Vertice start;
		private Vertice end;

		private Edge(Vertice start, Vertice end) {
			this.start = start;
			this.end = end;
		}

		public void setEnd(Vertice vertice) {
			this.end = vertice;
		}

		@Override
		public String toString() {
			return "(" + start + " -> " + end + ")";
		}
	}

	private final Map<Integer, Vertice> vertices = new HashMap<Integer, Vertice>();

	@Override
	public void connect(I one, I two) {
		connectAndGet(one, two);
	}

	protected Edge connectAndGet(I one, I two) {
		Edge edge = getNewEdge(one, two);
		edge.start.out.add(edge);
		edge.end.in.add(edge);
		return edge;
	}

	private Edge getNewEdge(I one, I two) {
		if (!contains(one) || !contains(two))
			throw new IllegalArgumentException();
		Vertice vOne = vertices.get(one.getId());
		Vertice vTwo = vertices.get(two.getId());
		return getNewEdge(vOne, vTwo);
	}

	protected Edge getNewEdge(Vertice vOne, Vertice vTwo) {
		return new Edge(vOne, vTwo);
	}

	@Override
	public ListIterator<I> getConnections(I item) {
		return new ConnectionsIterator(vertices.get(item.getId()));
	}

	@Override
	public I get(I item) {
		Vertice vertice = vertices.get(item.getId());
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
	public boolean contains(Object obj) {
		if (!(obj instanceof Id))
			return false;
		final Id item = (Id)obj;
		return vertices.containsKey(item.getId());
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
		return new VerticesIterator(vertices.values().iterator());
	}

	@Override
	public boolean remove(Object obj) {
		if (!(obj instanceof Id))
			return false;
		final Id item = (Id) obj;
		final Vertice vertice = vertices.remove(item.getId());
		final boolean result = vertice != null;
		if (result)
			removeEdges(vertice);
		return result;
	}

	private void removeEdges(Vertice vertice) {
		for (Edge edge : vertice.out)
			edge.end.in.remove(edge);
		for (Edge edge : vertice.in) {
			edge.start.out.remove(edge);
		}
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
		for (Vertice vertice : vertices.values()) {
			int sizeIn = vertice.in.size();
			int sizeOut = vertice.out.size();
			for (int i = 0; i < sizeIn; i++)
				vertice.out.addLast(vertice.in.pollFirst());
			for (int i = 0; i < sizeOut; i++)
				vertice.in.addLast(vertice.out.pollFirst());
		}
	}

}
