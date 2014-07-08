package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;

public interface Graph<I, T extends Id<I>> extends Collection<T> {
	Edge<I,T> connect(T one, T two);
	T get(I id);
	Collection<Edge<I,T>> getIn(T item);
	Collection<Edge<I,T>> getOut(T item);
}
