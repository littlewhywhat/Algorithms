package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;

public interface Graph<I, T extends Id<I>, E extends Edge<I,T>> extends Collection<T> {
	E connect(T one, T two);
	T get(I id);
	Collection<E> getIn(T item);
	Collection<E> getOut(T item);
}
