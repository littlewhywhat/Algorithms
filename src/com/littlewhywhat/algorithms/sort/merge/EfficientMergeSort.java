package com.littlewhywhat.algorithms.sort.merge;

public class EfficientMergeSort extends AbstractMergeSort {

	interface Splitter {
		int getItem();

		int getIndex();

		Splitter getNextSplitter();

		Splitter getPrevSplitter();

		int swapItem(int newItem);

		void move();

		void addBefore(int itemId);

		void addAfter(int itemId);

	}

	interface ArraySplitterList {
		void setArray(int[] array);

		int sizeCache();

		int size();

		void addFirst(int itemId);

		void addLast(int itemId);

		Splitter getFirst();

		Splitter getLast();

		Splitter get(int index);

		void remove(int index);

		void clean();

		void removeAll();
	}

	private ArraySplitterList splitterList = new SimpleArraySplitterList();
	private int stepsToLast;

	public int getGenId() {
		return splitterList.sizeCache();
	}

	@Override
	protected void merge(int firstHalfStart,
			int secondHalfStart, int secondHalfLength) {
		int sumLength = secondHalfStart - firstHalfStart + secondHalfLength;
		if (sumLength < 400)
			insertionSort(firstHalfStart, firstHalfStart + sumLength);
		else
			mergeSort(firstHalfStart, secondHalfStart,
					secondHalfLength);

	}

	private void insertionSort(int start, int end) {
		int[] array = getOutput();
		for (int j = start + 1; j < end; j++) {
			int newItem = array[j];
			int i = j - 1;
			while (i > start - 1 && newItem < array[i]) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = newItem;
		}
	}

	private void mergeSort(int firstHalfStart,
			int secondHalfStart, int secondHalfLength) {
		int afterLastIndex = secondHalfStart + secondHalfLength;
		int cryteria = 1;
		stepsToLast = 0;
		if (afterLastIndex != getData().length) {
			splitterList.addFirst(afterLastIndex);
			cryteria++;
			stepsToLast = 1;
		}
		splitterList.addFirst(secondHalfStart);
		splitterList.addFirst(firstHalfStart);
		while (splitterList.size() > cryteria) {
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

	private Splitter getSplitterWithMin() {
		Splitter last = getLast();
		Splitter preLast = last.getPrevSplitter();
		if (last.getItem() > preLast.getItem())
			return preLast;
		return last;
	}

	private Splitter getLast() {
		return splitterList.get(splitterList.size() - stepsToLast - 1);
	}
	
	@Override
	protected void setup() {
		setOutput(getData());
		splitterList.setArray(getOutput());
	}
}
