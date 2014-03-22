package com.littlewhywhat.datastructure;

public interface ArrayDivider<T> {
	T getItem();
	void goToPart(int index);
	void setArray(T[] array);
	void setNumberOfParts(int numberOfParts);
	void setStartIndex(int startIndex);
	boolean partHasItems();
}
