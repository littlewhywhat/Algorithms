package com.littlewhywhat.algorithms.graphs.csc;

import java.util.Collection;
import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.Connection;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;

public class ReversibleGraph extends SearchGraph {

	public ReversibleGraph() {
		super();
	}

	public void reverse() {
		Vertice border = getNewVertice(this.size());
		Connection borderConnection = getNewConnection(border);
		Collection<Vertice> vertices = getVertices().values();
		for (Vertice vertice : vertices) {
			getLinkedConnections(vertice).addLast(borderConnection);
		}
		LinkedList<Connection> connections;
		for (Vertice vertice : vertices) {
			connections = getLinkedConnections(vertice);
			Vertice connection;
			while (connections.peekFirst().getVertice() != border) {
				connection = connections.pollFirst().getVertice();
				getLinkedConnections(connection).addLast(getNewConnection(vertice));
			}
			connections.pollFirst();
		}
	}
}
