package com.littlewhywhat.algorithms.graphs.csc;

import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;

public class ReversibleGraph extends SearchGraph {	
	
	public ReversibleGraph(int size) {
		super(size);
	}

	public void reverse() {
		Vertice border = getNewVertice(this.size());
		for (Vertice vertice : this) {
			this.getConnections(vertice).addLast(border);
		}
		for (Vertice vertice : this) {
			Vertice connection = this.getConnections(vertice).pollFirst();
			while (connection != border)
				this.getConnections(connection).addLast(vertice);
		}
	}
}
