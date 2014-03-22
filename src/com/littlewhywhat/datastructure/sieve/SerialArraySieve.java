package com.littlewhywhat.datastructure.sieve;

import com.littlewhywhat.datastructure.ArraySieve;

public class SerialArraySieve<T> implements ArraySieve<T> {
	private T[] array;
	private int interval;
	private int marker;
	private int round;

	@Override
	public T getItem() {
		T item = array[marker];
		increment();
		return item;
	}

	@Override
	public boolean hasItems() {
		return round != interval;
	}

	private void increment() {
		marker += interval;
		if (marker >= array.length) {
			round++;
			marker = round;
		}
	}

	@Override
	public void setArray(T[] array) {
		this.array = array;
	}

	@Override
	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public void reset() {
		marker = 0;
		round = 0;
	}

	@Override
	public T[] getArray() {
		return this.array;
	}

}
