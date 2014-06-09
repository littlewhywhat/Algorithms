package com.littlewhywhat.algorithms.graphs;

public interface Vertice {
	Vertice getConnection(int index);
	Iterable<Vertice> getConnections();
	int sizeConnections();
	int getIndex();
}
