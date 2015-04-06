package com.littlewhywhat.algorithms.salesman;

public interface LinkedList<T extends LinkedNode> {
	void add(T node);
	void removeLast();
	boolean isEmpty();
	T getFirst();
}
