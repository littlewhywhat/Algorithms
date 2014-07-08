package com.littlewhywhat.algorithms.graphs;

public interface DirectedGraph<I, T extends Id<I>, E extends Edge<I,T>> extends Graph<I, T, E> {
	void reverse();
}
