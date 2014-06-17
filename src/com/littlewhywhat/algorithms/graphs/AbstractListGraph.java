package com.littlewhywhat.algorithms.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractListGraph implements Graph {

	public class SimpleVertice implements Vertice {

		protected final List<Vertice> connections = getNewConnectionsList();
		private final ConnectionsList connectionsList = new SimpleConnectionsList(
				connections);
		private final int index;

		public SimpleVertice(int index) {
			this.index = index;
		}

		@Override
		public ConnectionsList getConnections() {
			return connectionsList;
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

	private final List<Vertice> vertices = new ArrayList<Vertice>();

	public AbstractListGraph() {
	}

	public AbstractListGraph(int size) {
		this.setSize(size);
	}

	@Override
	public void setSize(int size) {
		if (this.vertices.isEmpty()) {
			for (int index = 0; index < size; index++)
				this.vertices.add(getNewVertice(index));
		}
	}

	protected Vertice getNewVertice(int index) {
		return new SimpleVertice(index);
	}

	protected abstract List<Vertice> getNewConnectionsList();

	protected List<Vertice> getConnections(Vertice vertice) {
		return ((SimpleVertice) vertice).connections;
	}

	@Override
	public Iterator<Vertice> iterator() {
		return this.vertices.iterator();
	}

	@Override
	public void connect(int one, int two) {
		SimpleVertice verticeOne = (SimpleVertice) this.getVertice(one);
		verticeOne.connections.add(this.getVertice(two));
	}

	@Override
	public Vertice getVertice(int index) {
		return this.vertices.get(index);
	}

	@Override
	public int size() {
		return this.vertices.size();
	}

}
