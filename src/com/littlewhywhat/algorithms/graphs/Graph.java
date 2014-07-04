package com.littlewhywhat.algorithms.graphs;

import java.util.Iterator;
import java.util.List;

public interface Graph<T> extends List<T> {
	void connect(int one, int two);
	Iterator<T> getConnections(T item);
}
