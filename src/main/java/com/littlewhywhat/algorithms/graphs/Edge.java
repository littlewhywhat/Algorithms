package com.littlewhywhat.algorithms.graphs;

public interface Edge<I, T extends Id<I>> {
	T getStart();
	T getEnd();
}
