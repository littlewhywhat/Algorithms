package com.littlewhywhat.algorithms.graphs;

public interface DirectedGraph<T extends Id> extends Graph<T> {
	void reverse();
}
