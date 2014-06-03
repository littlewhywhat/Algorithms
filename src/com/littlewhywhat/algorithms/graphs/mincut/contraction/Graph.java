package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.LinkedList;

public class Graph {
	class Vertice {
		private LinkedList<Vertice> connections = new LinkedList<Vertice>();
		private Vertice merged;
		private int index;
		Vertice(int index) {
			this.index = index;
		}
		
		private Vertice leader() {
			Vertice leader = this;
			while (leader.merged != null)
				leader = leader.merged;
			return leader;
		}

		@Override
		public String toString() {
			return "[" +  index +  ">" + leader().index + "]";
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
			if (!(obj instanceof Vertice)) {
				return false;
			}
			Vertice other = (Vertice) obj;
			if (index != other.index) {
				return false;
			}
			return true;
		}


		int connectionsCount() {
			return this.connections.size();
		}

		Vertice getConnection(int index) {
			return this.connections.get(index).leader();
		}

	}
	
	private Vertice[] vertices;
	
	Graph(int size) {
		this.vertices = new Vertice[size];
		for (int i = 0; i < size; i++)
			this.vertices[i] = new Vertice(i);
	}
	
	Vertice getVertice(int index) {
		return this.vertices[index].leader();
	}
	
	int size() {
		return this.vertices.length;
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
		one.connections.clear();
	}
	
	void connect(int one, int two) {
		this.getVertice(one).connections.push(this.getVertice(two));
	}
}
