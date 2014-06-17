package com.littlewhywhat.algorithms.graphs.io;

public abstract class AbstractGraphFiller implements GraphFiller {

	protected int convertVerticeIndex(int verticeIndex) {
		return verticeIndex - 1;
	}
}
