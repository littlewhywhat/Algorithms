package com.littlewhywhat.algorithms.graphs;

public interface DirectedGraph<I, T extends Id<I>> extends Graph<I, T> {
	void reverse();
}
