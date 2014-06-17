package com.littlewhywhat.algorithms.graphs;

public interface ConnectionsList extends Iterable<Vertice> {

	Vertice get(int index);

	int size();
}
