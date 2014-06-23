package com.littlewhywhat.algorithms.graphs.csc;

import java.util.Collection;
import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.SimpleGraph;
import com.littlewhywhat.algorithms.graphs.Vertice;

public class ReversibleGraph extends SimpleGraph {

	public ReversibleGraph() {
		super();
	}

	public void reverse() {
		Vertice border = getNewVertice(this.size());
		Collection<Vertice> vertices = getVertices().values();
		for (Vertice vertice : vertices) {
			getLinkedConnections(vertice).addLast(border);
		}
		LinkedList<Vertice> connections;
		for (Vertice vertice : vertices) {
			connections = getLinkedConnections(vertice);
			Vertice connection;
			while (connections.peekFirst() != border) {
				connection = connections.pollFirst();
				getLinkedConnections(connection).addLast(vertice);
			}
			connections.pollFirst();
		}
	}
}
