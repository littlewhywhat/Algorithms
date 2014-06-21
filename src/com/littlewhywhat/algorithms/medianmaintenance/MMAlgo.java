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
		private int median;

		private MedianMaintenance(int initMedian) {
			this.median = initMedian;
		}

		private int getMedian() {
			return this.median;
		}

		private int getSizeDiff() {
			return heapMin.size() - heapMax.size();
		}

		private boolean heapMaxOverflow() {
			return getSizeDiff() < 0;
		}

		private boolean heapMinOverflow() {
			return getSizeDiff() > 1;
		}

		private void insert(int item) {
			if (item > this.median)
				heapMin.insert(item);
			else
				heapMax.insert(item);
			this.stable();
		}

		private void stable() {
			if (heapMaxOverflow()) {
				stable(heapMin, heapMax);
			} else if (heapMinOverflow())
				stable(heapMax, heapMin);
		}

		private void stable(Heap<Integer> underflow, Heap<Integer> overflow) {
			underflow.insert(this.median);
			this.median = overflow.poll();
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
