package com.littlewhywhat.datastructure;

import java.util.Iterator;

public class ArrayPartIterator<E> implements Iterator<E> {

	private ArrayPart<E> arrayPart;
	private int index = -1;
	
	public ArrayPartIterator(ArrayPart<E> arrayPart) {
		this.arrayPart = arrayPart;
	}	

	@Override
	public boolean hasNext() {
		return this.index + 1 < this.arrayPart.size();
	}

	@Override
	public E next() {
		this.index++;
		return this.arrayPart.get(index);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}

}
