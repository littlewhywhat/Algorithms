package com.littlewhywhat.datastructure.collection;

import com.littlewhywhat.datastructure.Array;

public interface ArrayCollection<E> extends Array<E> {
	public void setArray(E[] array);

	public E[] getArray();

	public boolean hasNext();

	public E next();

	public void reset();

}
