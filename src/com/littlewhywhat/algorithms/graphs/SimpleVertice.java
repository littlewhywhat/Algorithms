package com.littlewhywhat.algorithms.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleVertice implements Vertice {

	private static class ConnectionsIterable implements Iterable<Vertice> {
		private Iterable<Vertice> connections;

		@Override
		public Iterator<Vertice> iterator() {
			return this.connections.iterator();
		}

		protected void setConnections(Iterable<Vertice> connections) {
			this.connections = connections;
		}
	}

	private static ConnectionsIterable iterable = new ConnectionsIterable();

	private final List<Vertice> connections = new LinkedList<Vertice>();
	private final int index;

	public SimpleVertice(int index) {
		this.index = index;
	}

	protected void connectTo(SimpleVertice vertice) {
		this.connections.add(vertice);
	}
	
	@Override
	public Vertice getConnection(int index) {
		return connections.get(index);
	}

	@Override
	public Iterable<Vertice> getConnections() {
		iterable.setConnections(this.connections);
		return iterable;
	}

	@Override
	public int sizeConnections() {
		return this.connections.size();
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
