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
	}

	private ArraySplitterList splitterList = new SimpleArraySplitterList();

	public int getGenId() {
		return splitterList.sizeCache();
	}

	@Override
	protected void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		int sumLength = firstHalfLength + secondHalfLength;
		if (sumLength < 1)
			insertionSort(firstHalfStart, firstHalfStart + sumLength);
		else
			mergeSort(firstHalfStart, firstHalfLength, secondHalfStart,
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

	private void mergeSort(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		int lastIndex = secondHalfStart + secondHalfLength;
		int stepsToLast = 0;
		Splitter border = null;
		if (lastIndex != getData().length) {
			splitterList.addFirst(lastIndex);
			stepsToLast++;
			border = splitterList.getFirst();
		}
		splitterList.addFirst(secondHalfStart);
		splitterList.addFirst(firstHalfStart);
		int cryteria = stepsToLast + 1;

		while (splitterList.size() > cryteria) {
			Splitter minPart = getMinLastOrPrelast(stepsToLast);
			int min = minPart.getItem();
			Splitter splitter = splitterList.getFirst();
			int swap = splitter.swapItem(minPart.getItem());
			Splitter nextSplitter;
			Splitter afterNextSplitter;
			splitter.move();
			while (swap != min) {
				nextSplitter = splitter.getNextSplitter();
				afterNextSplitter = nextSplitter.getNextSplitter();
				if (caseLast(afterNextSplitter, border)
						|| casePreLast(afterNextSplitter, stepsToLast, minPart)) {
					nextSplitter.addBefore(nextSplitter.getIndex());
					afterNextSplitter = nextSplitter;
					nextSplitter = nextSplitter.getPrevSplitter();
				}
				splitter = afterNextSplitter;
				swap = splitter.swapItem(swap);
				splitter.move();
			}
			// if (chain.size() > 1) {
			// System.out.println(chain);
			// for (int i = chain.getFirst().getItemId(); i <
			// chain.getLast().getItemId() + chain.getLast().getLength() - 1;
			// i++)
			// System.out.print(getOutput()[i] + " ");
			// System.out.println();
			// }
			splitterList.clean();
		}
		for (int i = 0; i < cryteria; i++)
			splitterList.remove(0);
	}

	private boolean casePreLast(Splitter afterNextSplitter, int stepsToLast,
			Splitter minPart) {
		return afterNextSplitter.equals(splitterList.get(splitterList.size() - stepsToLast - 1))
				&& minPart.equals(splitterList.get(splitterList.size()
						- stepsToLast - 2));
	}

	private boolean caseLast(Splitter afterNextSplitter, Splitter border) {
		return afterNextSplitter == null || afterNextSplitter.equals(border);
	}

	private Splitter getMinLastOrPrelast(int stepsToLast) {
		Splitter last = splitterList.get(splitterList.size() - stepsToLast - 1);
		Splitter preLast = last.getPrevSplitter();
		if (last.getItem() > preLast.getItem())
			return preLast;
		return last;
	}

	@Override
	protected void setup() {
		setOutput(getData());
		splitterList.setArray(getOutput());
	}
}
