package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.sort.InsertionSort;

public class EfficientMergeSort extends AbstractMergeSort {

	interface ArraySplitterList {
		void addFirst(int itemId);

		void addLast(int itemId);

		void clean();

		Splitter get(int index);

		Splitter getFirst();

		Splitter getLast();

		void remove(int index);

		void removeAll();

		void setArray(int[] array);

		int size();

		int sizeCache();
	}

	interface Splitter {
		void addAfter(int itemId);

		void addBefore(int itemId);

		int getIndex();

		int getItem();

		Splitter getNextSplitter();

		Splitter getPrevSplitter();

		void move();

		int swapItem(int newItem);

	}

	private ArraySplitterList splitterList = new SimpleArraySplitterList();
	private InsertionSort insertionSort = new InsertionSort();
	private int[] insertionSortConfig = new int[2];
	private final int STEPS_TO_LAST = 2;
	private final int SIZE_WHEN_SORTED = 2;

	public int getGenId() {
		return splitterList.sizeCache();
	}

	private Splitter getLast() {
		return splitterList.get(splitterList.size() - STEPS_TO_LAST);
	}

	private Splitter getSplitterWithMin() {
		Splitter last = getLast();
		Splitter preLast = last.getPrevSplitter();
		if (last.getItem() > preLast.getItem())
			return preLast;
		return last;
	}

	private void hopToFreed(int swapItem, Splitter splitter, Splitter splitterWithMin) {
		Splitter nextSplitter;
		Splitter afterNextSplitter;
		while (splitter != splitterWithMin) {
			nextSplitter = splitter.getNextSplitter();
			if (nextSplitter.equals(splitterWithMin)) {
				nextSplitter.addBefore(nextSplitter.getIndex());
				continue;
			}
			afterNextSplitter = nextSplitter.getNextSplitter();
			swapItem = afterNextSplitter.swapItem(swapItem);
			afterNextSplitter.move();
			splitter = afterNextSplitter;
		}		
	}

	@Override
	protected void merge(int firstHalfStart, int secondHalfStart,
			int secondHalfLength) {
		int sumLength = secondHalfStart - firstHalfStart + secondHalfLength;
		if (sumLength < 700) {
			insertionSortConfig[0] = firstHalfStart;
			insertionSortConfig[1] = firstHalfStart + sumLength;
			insertionSort.setConfig(insertionSortConfig);
			insertionSort.execute();
		} else
			mergeSort(firstHalfStart, secondHalfStart, secondHalfLength);

	}

	private void mergeSort(int firstHalfStart, int secondHalfStart,
			int secondHalfLength) {
		splitterList.addFirst(secondHalfStart + secondHalfLength);
		splitterList.addFirst(secondHalfStart);
		splitterList.addFirst(firstHalfStart);
		while (splitterList.size() != SIZE_WHEN_SORTED) {
			Splitter splitterWithMin = getSplitterWithMin();
			Splitter firstSplitter = splitterList.getFirst();
			int swapItem = firstSplitter.swapItem(splitterWithMin.getItem());
			firstSplitter.move();
			hopToFreed(swapItem, firstSplitter, splitterWithMin);
			splitterList.clean();
		}
		splitterList.removeAll();
	}

	@Override
	protected void setup() {
		setOutput(getData());
		splitterList.setArray(getOutput());
		insertionSort.setData(getOutput());
	}
}
