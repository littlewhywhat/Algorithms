package com.littlewhywhat.datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleHeap<T> implements Heap<T> {

	private static final boolean MAX_HEAP_CODE = true;
	private static final boolean MIN_HEAP_CODE = false;
	public static <B> SimpleHeap<B> getMaxHeap(Comparator<B> comparator) {
		return new SimpleHeap<B>(comparator, MAX_HEAP_CODE);
	}
	public static <B> SimpleHeap<B> getMinHeap(Comparator<B> comparator) {
		return new SimpleHeap<B>(comparator, MIN_HEAP_CODE);
	}
	private Comparator<T> comparator;

	private boolean isMax;

	private final List<T> items = new ArrayList<T>();

	private SimpleHeap(Comparator<T> comparator, boolean isMax) {
		this.isMax = isMax;
		this.setComparator(comparator);
	}

	private void bubbleUp(int itemIndex) {
		int parentIndex = getParentIndex(itemIndex);
		while (itemIndex > 0 && !isInHeapCondition(itemIndex)) {
			swap(parentIndex, itemIndex);
			itemIndex = parentIndex;
			parentIndex = getParentIndex(itemIndex);
		}
	}

	private int getChildOne(int itemIndex) {
		return 2 * itemIndex + 1;
	}

	private int getChildTwo(int itemIndex) {
		return 2 * itemIndex + 2;
	}

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
		System.out.println(this);
	}

	private boolean isInHeapCondition(int child) {
		int parent = getParentIndex(child);
		return isInHeapCondition(parent, child);
	}

	private boolean isInHeapCondition(int one, int two) {
		if (one >= this.items.size())
			return true;
		if (two >= this.items.size())
			return true;
		if (isMax) {
			if (this.comparator.compare(this.items.get(one),
					this.items.get(two)) != -1)
				return true;
			return false;
		}
		if (this.comparator.compare(this.items.get(one), this.items.get(two)) != 1)
			return true;
		return false;
	}

	public T peek() {
		if (this.items.isEmpty())
			return null;
		return this.items.get(0);
	}

	@Override
	public T poll() {
		final int firstIndex = 0;
		final T item = this.items.get(firstIndex);
		removeAt(firstIndex);
		return item;
	}

	private void pushDown(int itemIndex) {
		int childOne = getChildOne(itemIndex);
		int childTwo = getChildTwo(itemIndex);
		int toSwap;
		while (!isInHeapCondition(childOne) || !isInHeapCondition(childTwo)) {
			if (isInHeapCondition(childOne, childTwo))
				toSwap = childOne;
			else
				toSwap = childTwo;
			swap(itemIndex, toSwap);
			itemIndex = toSwap;
			childOne = getChildOne(itemIndex);
			childTwo = getChildTwo(itemIndex);
		}
	}

	@Override
	public void remove(T item) {
		final int itemIndex = this.items.indexOf(item);
		this.removeAt(itemIndex);
	}

	private void removeAt(int itemIndex) {
		int lastIndex = this.items.size() - 1;
		swap(itemIndex, lastIndex);
		System.out.println("after swap" + this);
		this.items.remove(lastIndex);
		pushDown(itemIndex);
		System.out.println("after push" + this);
	}

	@Override
	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	private void swap(int parentIndex, int itemIndex) {
		T temp = this.items.get(parentIndex);
		this.items.set(parentIndex, this.items.get(itemIndex));
		this.items.set(itemIndex, temp);
	}

	@Override
	public String toString() {
		return this.items.toString();
	}
	@Override
	public int size() {
		return this.items.size();
	}

}
