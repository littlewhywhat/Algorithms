package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.Graph;

public class ContractionGraph implements Graph {
	class Vertice {
		private LinkedList<Vertice> connections = new LinkedList<Vertice>();
		private int index;
		private Vertice merged;

		Vertice(int index) {
			this.index = index;
		}

		int connectionsCount() {
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
		
		Vertice getConnection(int index) {
			return this.connections.get(index).leader();
		}

		@Override
		public int hashCode() {
			return index;
		}

		private Vertice leader() {
			Vertice leader = this;
			while (leader.merged != null)
				leader = leader.merged;
			return leader;
		}

		@Override
		public String toString() {
			return "[" + index + ">" + leader().index + "]";
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

	Vertice getVertice(int index) {
		return this.vertices[index].leader();
	}

	void merge(Vertice one, Vertice two) {
		one.merged = two;
		int size = two.connections.size();
		for (int i = 0; i < size; i++) {
			Vertice vertice = two.connections.pollFirst();
			vertice = vertice.leader();
			if (!vertice.equals(two))
				two.connections.addLast(vertice);
		}
		for (Vertice vertice : one.connections) {
			vertice = vertice.leader();
			if (!vertice.equals(two))
				two.connections.addLast(vertice);
		}
		one.connections = null;
	}

	int size() {
		return this.vertices.length;
	}
}
