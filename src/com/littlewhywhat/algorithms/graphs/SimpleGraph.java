package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class SimpleGraph implements Graph {

	private class GraphIterator implements Iterator<Vertice> {

		private final Iterator<Vertice> iterator;

		private GraphIterator(Iterator<Vertice> iterator) {
			this.iterator = iterator;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Vertice next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public class SimpleConnection implements Connection {

		private final Vertice vertice;

		public SimpleConnection(Vertice vertice) {
			this.vertice = vertice;
		}

		@Override
		public Vertice getVertice() {
			return vertice;
		}

	}

	public class SimpleVertice implements Vertice {

		private final UnmodifiableList<Connection> connections = new UnmodifiableList<Connection>(
				new LinkedList<Connection>());
		private final int index;

		public SimpleVertice(int index) {
			this.index = index;
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
		public int getIndex() {
			return this.index;
		}

		@Override
		public int hashCode() {
			return index;
		}

		@Override
		public String toString() {
			return String.valueOf(index);
		}

	};

	private final HashMap<Integer, Vertice> vertices;

	public SimpleGraph() {
		vertices = new HashMap<Integer, Vertice>();
	}

	@Override
	public void clear() {
		vertices.clear();
	}

	@Override
	public Connection connect(int one, int two) {
		if (!containsKey(one))
			vertices.put(one, getNewVertice(one));
		if (!containsKey(two))
			vertices.put(two, getNewVertice(two));
		SimpleVertice verticeOne = (SimpleVertice) this.get(one);
		Connection connection = getNewConnection(this.get(two));
		getLinkedConnections(verticeOne).add(connection);
		return connection;
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
	public UnmodifiableList<Connection> getConnections(Vertice vertice) {
		return getUnmodifiableConnections(vertice);
	}

	protected LinkedList<Connection> getLinkedConnections(Vertice vertice) {
		return (LinkedList<Connection>) getUnmodifiableConnections(vertice)
				.getList();
	}

	protected Connection getNewConnection(Vertice vertice) {
		return new SimpleConnection(vertice);
	}

	protected Vertice getNewVertice(int index) {
		return new SimpleVertice(index);
	}

	private UnmodifiableList<Connection> getUnmodifiableConnections(
			Vertice vertice) {
		return ((SimpleVertice) vertice).connections;
	}

	protected HashMap<Integer, Vertice> getVertices() {
		return vertices;
	}

	@Override
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public Iterator<Vertice> iterator() {
		return new GraphIterator(vertices.values().iterator());
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
