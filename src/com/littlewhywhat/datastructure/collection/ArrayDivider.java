package com.littlewhywhat.datastructure.collection;

import java.util.ArrayList;
import java.util.List;

import com.littlewhywhat.datastructure.Array;
import com.littlewhywhat.datastructure.ArrayPart;

public class ArrayDivider<E> {

	private CycleResolver indexResolver;
	private E[] array;
	private List<ArrayPart<E>> arrayParts;
	private int startIndex;

	
	public static <T> ArrayDivider<T> getInstance(T[] array, int numberOfParts) {
		return new ArrayDivider<T>(array, numberOfParts);
	}
	
	private ArrayDivider(E[] array, int numberOfParts) {
		super();
		this.setArray(array);
		this.setNumberOfParts(numberOfParts);
	}

	public Array<E> getPart(int partIndex) {
		return this.arrayParts.get(partIndex);
	}

	public void setNumberOfParts(int numberOfParts) {
		this.arrayParts = new ArrayList<ArrayPart<E>>();
		for (int partIndex = 0; partIndex < numberOfParts; partIndex++)
			arrayParts.add(ArrayPart.getInstance(this.array));
		this.setIndicesToArrayParts();
	}

	private void setIndicesToArrayParts() {
		int division = this.array.length / arrayParts.size();
		int startPartIndex = this.startIndex;
		int endPartIndex;
		ArrayPart<E> part;
		for (int i = 0; i < arrayParts.size() - 1; i++) {
			endPartIndex = startPartIndex + division - 1;
			part = arrayParts.get(i);
			part.setStartIndex(this.indexResolver.resolveIndex(startPartIndex));
			part.setEndIndex(this.indexResolver.resolveIndex(endPartIndex));
			startPartIndex = endPartIndex + 1;
		}
		part = arrayParts.get(arrayParts.size() - 1);
		part.setStartIndex(this.indexResolver.resolveIndex(startPartIndex));
		part.setEndIndex(this.indexResolver.resolveIndex(this.startIndex - 1));
	}

	public void setArray(E[] array) {
		this.indexResolver = new CycleResolver(array.length);
		this.array = array;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
		this.setIndicesToArrayParts();
	}

}
