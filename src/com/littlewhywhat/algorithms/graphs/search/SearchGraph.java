package com.littlewhywhat.algorithms.graphs.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.littlewhywhat.algorithms.graphs.Graph;

public class SearchGraph implements Graph {
	public class Vertice implements com.littlewhywhat.algorithms.graphs.Vertice {
		private boolean isExplored = false;
		private final int index;
		private List<com.littlewhywhat.algorithms.graphs.Vertice> connections = new LinkedList<com.littlewhywhat.algorithms.graphs.Vertice>();

		public Vertice(int index) {
			this.index = index;
		}

		public void markAsExplored() {
			isExplored = true;
		}

		public Iterable<com.littlewhywhat.algorithms.graphs.Vertice> getConnections() {
			return this.connections;
		}

		public boolean isExplored() {
			return this.isExplored;
		}

		@Override
		public String toString() {
			return index + " [" + isExplored + "]";
		}

		public int getIndex() {
			return this.index;
		}

		@Override
		public com.littlewhywhat.algorithms.graphs.Vertice getConnection(
				int index) {
			return this.connections.get(index);
		}

		@Override
		public int sizeConnections() {
			return this.connections.size();
		}

	}

	private final Vertice[] vertices;

	public SearchGraph(int size) {
		this.vertices = new Vertice[size];
		for (int index = 0; index < size; index++)
			this.vertices[index] = new Vertice(index);
	}

	public Vertice getVertice(int i) {
		return vertices[i];
	}

	@Override
	public void connect(int one, int two) {
		Vertice oneVertice = this.getVertice(one);
		Vertice twoVertice = this.getVertice(two);
		List<com.littlewhywhat.algorithms.graphs.Vertice> connectionsOfOne = oneVertice.connections;
		connectionsOfOne.add(twoVertice);
	}

	@Override
	public Iterator<com.littlewhywhat.algorithms.graphs.Vertice> iterator() {
		return new Iterator<com.littlewhywhat.algorithms.graphs.Vertice>() {

			private int i = 0;

			@Override
			public boolean hasNext() {
				return i < vertices.length;
			}

			@Override
			public Vertice next() {
				return getVertice(i++);
			}

			@Override
			public void remove() {
			}

		};
	}

	public int size() {
		return vertices.length;
	}
	
	public void reset() {
		for (com.littlewhywhat.algorithms.graphs.Vertice vertice : this) {
			((Vertice)vertice).isExplored = false;
		}
	}
}
