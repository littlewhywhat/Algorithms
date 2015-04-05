package com.littlewhywhat.algorithms.medianmaintenance;

import java.util.Comparator;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.datastructure.Heap;
import com.littlewhywhat.datastructure.SimpleHeap;

public class MMAlgo extends AbstractAlgorithm<Void, int[], Integer> {

	private class MedianMaintenance {
		private final Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer one, Integer two) {
				return one.compareTo(two);
			}
		};
		private final Heap<Integer> heapMax = SimpleHeap.getMaxHeap(comparator);
		private final Heap<Integer> heapMin = SimpleHeap.getMinHeap(comparator);

		private MedianMaintenance(int initMedian) {
			this.heapMax.insert(initMedian);
		}

		private int getMedian() {
			return this.heapMax.peek();
		}

		private int getSizeDiff() {
			return heapMin.size() - heapMax.size();
		}

		private boolean heapMaxOverflow() {
			return getSizeDiff() < -1;
		}

		private boolean heapMinOverflow() {
			return getSizeDiff() > 0;
		}

		private void insert(int item) {
			if (item > getMedian())
				insertInHeapMin(item);
			else
				insertInHeapMax(item);
			this.stable();
		}

		private void insertInHeapMax(int item) {
			int median = this.heapMax.poll();
			heapMax.insert(item);
			heapMax.insert(median);
		}

		private void insertInHeapMin(int item) {
			heapMin.insert(item);
		}

		private void stable() {
			if (heapMaxOverflow()) {
				stable(heapMax, heapMin);
			} else if (heapMinOverflow())
				stable(heapMin, heapMax);
		}

		private void stable(Heap<Integer> overflow, Heap<Integer> underflow) {
			underflow.insert(overflow.poll());
		}
	}

	@Override
	public void execute() {
		int[] array = getData();
		MedianMaintenance maintenance = new MedianMaintenance(array[0]);
		int result = array[0];
		for (int i = 1; i < array.length; i++) {
			maintenance.insert(array[i]);
			result += maintenance.getMedian();
		}
		setOutput(result % 10000);
	}
}
