package com.littlewhywhat.datastructure;

import java.util.Comparator;

public interface Heap<T> {
	void setComparator(Comparator<T> comparator);
	void insert(T item);
	void remove(T item);
	T poll();
	T peek();
	int size();
}
