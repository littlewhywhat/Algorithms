package com.littlewhywhat.algorithms.graphs.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.littlewhywhat.algorithms.graphs.Graph;

public class SearchGraph implements Graph, Iterable<SearchGraph.Vertice> {
	public class Vertice {
		private boolean isExplored = false;
		private final int index;
		private List<Vertice> connections = new LinkedList<Vertice>();
		
		public Vertice(int index) {
			this.index = index;
		}
		public void markAsExplored() {
			isExplored = true;
		}

		public List<Vertice> getConnections() {
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
		List<Vertice> connectionsOfOne = oneVertice.getConnections();
		connectionsOfOne.add(twoVertice);
	}

	@Override
	public Iterator<Vertice> iterator() {
		return new Iterator<Vertice>() {

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
			public void remove() {	}
			
		};
	}

	public int size() {
		return vertices.length;
	}
}
