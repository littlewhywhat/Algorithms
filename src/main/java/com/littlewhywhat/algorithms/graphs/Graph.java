package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.List;

public interface Graph<I, T extends Id<I>, E extends Edge<I,T>> extends Collection<T> {
	E connect(T one, T two);
	T get(I id);
	List<E> getIn(T item);
	List<E> getOut(T item);
	List<E> edges();
}
