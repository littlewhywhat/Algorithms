package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleGraph implements Graph {

	public class SimpleVertice implements Vertice {

		private final UnmodifiableVerticeList connections = new UnmodifiableVerticeList(new LinkedList<Vertice>());
		private final int index;

		public SimpleVertice(int index) {
			this.index = index;
		}

		@Override
		public int getIndex() {
			return this.index;
		}

		@Override
		public int hashCode() {
			return index;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SimpleVertice)) {
				return false;
			}
			SimpleVertice other = (SimpleVertice) obj;
			if (index != other.index) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return String.valueOf(index);
		}

	}

	private final HashMap<Integer,Vertice> vertices;
	
	public SimpleGraph() {
		vertices = new HashMap<Integer, Vertice>();
	}

	protected HashMap<Integer, Vertice> getVertices() {
		return vertices;
	}
	
	protected Vertice getNewVertice(int index) {
		return new SimpleVertice(index);
	}

	public List<Vertice> getConnections(Vertice vertice) {
		return getUnmodifiableConnections(vertice);
	}
	
	private UnmodifiableVerticeList getUnmodifiableConnections(Vertice vertice) {
		return ((SimpleVertice) vertice).connections;
	}
	
	protected LinkedList<Vertice> getLinkedConnections(Vertice vertice) {
		return (LinkedList<Vertice>) getUnmodifiableConnections(vertice).getList();
	}

	@Override
	public void connect(int one, int two) {
		if (!containsKey(one))
			vertices.put(one, getNewVertice(one));
		if (!containsKey(two))
			vertices.put(two, getNewVertice(two));
		SimpleVertice verticeOne = (SimpleVertice) this.get(one);
		verticeOne.connections.add(this.get(two));
	}

	@Override
	public void clear() {
		vertices.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return vertices.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return vertices.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<Integer, Vertice>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Vertice get(Object index) {
		return vertices.get(index);
	}

	@Override
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public Set<Integer> keySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Vertice put(Integer arg0, Vertice arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends Vertice> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Vertice remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return vertices.size();
	}

	@Override
	public Collection<Vertice> values() {
		throw new UnsupportedOperationException();
	}

}
