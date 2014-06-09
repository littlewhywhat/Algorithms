package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.Iterator;
import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.Graph;

public class ContractionGraph implements Graph {
	class Vertice implements com.littlewhywhat.algorithms.graphs.Vertice {
		private LinkedList<com.littlewhywhat.algorithms.graphs.Vertice> connections = new LinkedList<com.littlewhywhat.algorithms.graphs.Vertice>();
		private int index;
		private Vertice mergedTo;

		Vertice(int index) {
			this.index = index;
		}

		public int sizeConnections() {
			return this.connections.size();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Vertice)) {
				return false;
			}
			Vertice other = (Vertice) obj;
			if (this.index != other.index) {
				return false;
			}
			return true;
		}
		
		public Vertice getConnection(int index) {
			return ((Vertice)this.connections.get(index)).leader();
		}

		@Override
		public int hashCode() {
			return index;
		}

		private Vertice leader() {
			Vertice leader = this;
			while (leader.mergedTo != null)
				leader = leader.mergedTo;
			return leader;
		}

		@Override
		public String toString() {
			return "[" + index + ">" + leader().index + "]";
		}

		@Override
		public Iterable<com.littlewhywhat.algorithms.graphs.Vertice> getConnections() {
			// TODO Auto-generated method stub
			return connections;
		}

		@Override
		public int getIndex() {
			return index;
		}

	}

	private Vertice[] vertices;

	ContractionGraph(int size) {
		this.vertices = new Vertice[size];
		for (int i = 0; i < size; i++)
			this.vertices[i] = new Vertice(i);
	}
	
	@Override
	public void connect(int one, int two) {
		this.getVertice(one).connections.push(this.getVertice(two));
	}

	public Vertice getVertice(int index) {
		return this.vertices[index].leader();
	}

	void merge(Vertice one, Vertice two) {
		one.mergedTo = two;
		int size = two.connections.size();
		for (int i = 0; i < size; i++) {
			Vertice vertice = (Vertice) two.connections.pollFirst();
			vertice = vertice.leader();
			if (!vertice.equals(two))
				two.connections.addLast(vertice);
		}
		for (com.littlewhywhat.algorithms.graphs.Vertice vertice : one.connections) {
			vertice = ((Vertice)vertice).leader();
			if (!vertice.equals(two))
				two.connections.addLast(vertice);
		}
		one.connections = null;
	}

	public int size() {
		return this.vertices.length;
	}

	@Override
	public Iterator<com.littlewhywhat.algorithms.graphs.Vertice> iterator() {
		return null;
	}
}
