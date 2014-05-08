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
	private int stepsToLast;

	public int getGenId() {
		return splitterList.sizeCache();
	}

	private Splitter getLast() {
		return splitterList.get(splitterList.size() - stepsToLast - 1);
	}

	private Splitter getSplitterWithMin() {
		Splitter last = getLast();
		Splitter preLast = last.getPrevSplitter();
		if (last.getItem() > preLast.getItem())
			return preLast;
		return last;
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
		int listSizeWhenSorted = 1;
		stepsToLast = 0;
		int indexAfterLast = secondHalfStart + secondHalfLength;
		if (indexAfterLast < getData().length) {
			splitterList.addFirst(indexAfterLast);
			listSizeWhenSorted++;
			stepsToLast++;
		}
		splitterList.addFirst(secondHalfStart);
		splitterList.addFirst(firstHalfStart);
		while (splitterList.size() != listSizeWhenSorted) {
			Splitter splitterWithMin = getSplitterWithMin();
			int minItem = splitterWithMin.getItem();
			int swapItem = splitterList.getFirst().swapItem(minItem);
			splitterList.getFirst().move();
			Splitter splitter = splitterList.getFirst();
			Splitter nextSplitter;
			Splitter afterNextSplitter;
			while (swapItem != minItem) {
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
