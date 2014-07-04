package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.Iterator;

public interface Graph<T extends Id> extends Collection<T> {
	void connect(int one, int two);
	Iterator<T> getConnections(T item);
	T get(int id);
}
