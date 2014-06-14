package com.littlewhywhat.datastructure;

import java.util.ArrayList;
import java.util.List;

public class SimpleHeap<T extends Comparable<T>> implements Heap<T> {

	private final List<T> items = new ArrayList<T>();

	private int getParentIndex(int index) {
		int parentIndex = index / 2;
		if (index % 2 == 0)
			return parentIndex - 1;
		return parentIndex;
	}

	@Override
	public void insert(T item) {
		int itemIndex = items.size();
		items.add(item);
		bubbleUp(itemIndex);
	}

	private void bubbleUp(int itemIndex) {
		int parentIndex = getParentIndex(itemIndex);
		while (itemIndex > 0 && compare(parentIndex, itemIndex) != -1) {
			swap(parentIndex, itemIndex);
			itemIndex = parentIndex;
			parentIndex = getParentIndex(itemIndex);
		}
	}

	private void swap(int parentIndex, int itemIndex) {
		T temp = this.items.get(parentIndex);
		this.items.set(parentIndex, this.items.get(itemIndex));
		this.items.set(itemIndex, temp);
	}

	private int compare(int one, int two) {
		return this.items.get(one).compareTo(this.items.get(two));
	}

	@Override
	public void remove(T item) {
		int itemIndex = this.items.indexOf(item);
		int lastIndex = this.items.size() - 1;
		swap(itemIndex, lastIndex);
		this.items.remove(lastIndex);
		pushDown(itemIndex);
	}

	private void pushDown(int itemIndex) {
		int childOne = getChildOne(itemIndex);
		int childTwo = getChildTwo(itemIndex);
		int toSwap;
		while ((childOne < this.items.size() && compare(itemIndex, childOne) == 1)
				|| (childTwo < this.items.size() && compare(itemIndex, childTwo) == 1)) {
			if (compare(childOne, childTwo) == 1)
				toSwap = childTwo;
			else
				toSwap = childOne;
			swap(itemIndex, toSwap);
			itemIndex = toSwap;
			childOne = getChildOne(itemIndex);
			childTwo = getChildTwo(itemIndex);
		}
	}

	private int getChildTwo(int itemIndex) {
		return 2 * itemIndex + 2;
	}

	private int getChildOne(int itemIndex) {
		return 2 * itemIndex + 1;
	}

	@Override
	public T poll() {
		T item = this.items.get(0);
		remove(this.items.get(0));
		return item;
	}

	@Override
	public String toString() {
		return this.items.toString();
	}

}
