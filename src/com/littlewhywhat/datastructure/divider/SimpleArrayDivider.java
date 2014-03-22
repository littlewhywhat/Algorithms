package com.littlewhywhat.datastructure.divider;

import com.littlewhywhat.datastructure.ArrayDivider;

public class SimpleArrayDivider<T> implements ArrayDivider<T> {

	private T[] array;
	private int interval;
	private int startIndex;
	private int currentIndex;
	private int count;

	@Override
	public T getItem() {
		increment();
		return array[currentIndex];
	}

	@Override
	public void goToPart(int index) {
		this.currentIndex = startIndex + (index - 1) * interval;
		if (this.currentIndex >= array.length)
			this.currentIndex -= array.length;
	}

	private void increment() {
		currentIndex++;
		count++;
		if (currentIndex == array.length)
			currentIndex = 0;
	}

	@Override
	public void setArray(T[] array) {
		this.array = array;
	}

	@Override
	public void setNumberOfParts(int numberOfParts) {
		this.interval = array.length / numberOfParts;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex - 1;
		this.currentIndex = this.startIndex;
	}

	@Override
	public boolean partHasItems() {
		return count < interval;
	}

}
