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
		
		LinkedList<Vertice> getConnections() {
			return this.connections;
		}
		
		void addToConnections(Vertice vertice) {
			this.connections.add(vertice);
		}
		
		void setMerged(Vertice vertice) {
			this.merged = vertice;
		}

		boolean isMerged() {
			return this.merged != null;
		}
		
		@Override
		public String toString() {
			return "[" + index + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + index;
			return result;
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
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (index != other.index) {
				return false;
			}
			return true;
		}

		private Graph getOuterType() {
			return Graph.this;
		}

		public Vertice getMerge() {
			return this.merged;
		}

		
		
	}
	private Vertice[] vertices;
	
	Graph(int size) {
		this.vertices = new Vertice[size];
		for (int i = 0; i < size; i++)
			this.vertices[i] = new Vertice(i);
	}
	
	Vertice getVertice(int index) {
		return this.vertices[index];
	}
	
	int size() {
		return this.vertices.length;
	}
}
