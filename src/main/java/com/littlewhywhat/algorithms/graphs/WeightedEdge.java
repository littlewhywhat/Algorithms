package com.littlewhywhat.algorithms.graphs;

public interface WeightedEdge<I, T extends Id<I>> extends Edge<I, T> {
	int getWeight();
	void lighten(int value);
	void load(int value);
}
