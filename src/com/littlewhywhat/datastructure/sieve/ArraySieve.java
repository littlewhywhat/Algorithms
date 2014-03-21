package com.littlewhywhat.datastructure.sieve;

public interface ArraySieve<T> {
	void setInterval(int sellsNumber);
	void setArray(T[] array);
	T[] getArray();
	T getItem();
	boolean hasItems();
	void reset();
}
