package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.ListIterator;

public interface Graph<T extends Id> extends Collection<T> {
	void connect(T one, T two);
	ListIterator<T> getConnections(T item);
	T get(T item);
}
