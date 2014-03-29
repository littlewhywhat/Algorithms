package com.littlewhywhat.datastructure;

public interface ArrayCollection<E> {
	public void setArray(E[] array);

	public E[] getArray();

	public boolean hasNext();

	public E next();

	public void reset();

	public int size();
}
