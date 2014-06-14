package com.littlewhywhat.datastructure;

public interface Heap<T extends Comparable<T>> {
	void insert(T item);
	void remove(T item);
	T poll();
}
