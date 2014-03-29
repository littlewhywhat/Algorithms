package com.littlewhywhat.datastructure.collection;

public abstract class AbstractArrayCollection<E> implements ArrayCollection<E> {

	private E[] array;

	@Override
	public void setArray(E[] array) {
		this.array = array;
	}

	@Override
	public E[] getArray() {
		return this.array;
	}

	@Override
	public int size() {
		return this.array.length;
	}
	
	@Override
	public void set(int index, E element) {
		this.getArray()[index] = element;	
	}

	@Override
	public E get(int index) {
		return this.getArray()[index];
	}

}
