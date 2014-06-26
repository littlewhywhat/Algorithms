package com.littlewhywhat.algorithms.graphs.csc;

import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.Connection;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;

public class ReversibleGraph extends SearchGraph {

	public ReversibleGraph() {
		super();
	}

	public void reverse() {
		final Connection border = getNewConnection(getNewVertice(this.size()));
		for (Vertice vertice : this) {
			getLinkedConnections(vertice).addLast(border);
		}
		LinkedList<Connection> connections;
		for (Vertice vertice : this) {
			connections = getLinkedConnections(vertice);
			while (connections.peekFirst() != border)
				reverseConnection(connections.pollFirst(), vertice);
			connections.pollFirst();
		}
	}

	private void reverseConnection(Connection connection, Vertice vertice) {
		LinkedList<Connection> connections = getLinkedConnections(connection
				.getVertice());
		connection.setVertice(vertice);
		connections.addLast(connection);
	}
}
