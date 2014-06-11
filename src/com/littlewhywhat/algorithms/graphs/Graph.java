package com.littlewhywhat.algorithms.graphs;

public interface Graph extends Iterable<Vertice> {
	void setSize(int size);
	void connect(int one, int two);
	Vertice getVertice(int index);
	int size();
}
