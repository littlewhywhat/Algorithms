package com.littlewhywhat.datastructure;

public interface Array<E> {
	int size();
	void set(int index, E element);
	E get(int index);
}
