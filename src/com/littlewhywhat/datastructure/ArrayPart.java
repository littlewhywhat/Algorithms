package com.littlewhywhat.datastructure;

import java.util.Iterator;

public class ArrayPart<E> implements Array<E>, Iterable<E> {
	public static <T> ArrayPart<T> getInstance(T[] array, int startIndex,
			int endIndex) {
		return new ArrayPart<T>(array, startIndex, endIndex);
	}

	public static <T> ArrayPart<T> getInstance(T[] array) {
		return new ArrayPart<T>(array, 0, array.length - 1);
	}

	private E[] array;
	private int startIndex;
	private int endIndex;

	private boolean isOnBorder;

	private ArrayPart(E[] array, int startIndex, int endIndex) {
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.setIsOnBorder();
	}

	@Override
	public E get(int index) {
		int realIndex = startIndex + index;
		if (realIndex < this.array.length)
			return array[realIndex];
		if (isOnBorder)
			return array[realIndex - this.array.length];
		return null;
	}

	public E[] getArray() {
		return this.array;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	@Override
	public void set(int index, E element) {
		array[startIndex + index] = element;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
		this.setIsOnBorder();
	}

	private void setIsOnBorder() {
		this.isOnBorder = startIndex > endIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
		this.setIsOnBorder();
	}

	@Override
	public int size() {
		if (isOnBorder)
			return this.array.length - this.startIndex + this.endIndex + 1;
		return Math.abs(endIndex - startIndex) + 1;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayPartIterator<E>(this);
	}

}
