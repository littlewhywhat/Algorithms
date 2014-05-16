package com.littlewhywhat.algorithms.sort;

import java.util.Random;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class QuickSort extends AbstractAlgorithm<Void, int[], int[]> {

	private Random random = new Random();

	@Override
	public void execute() {
		recursiveCall(0, getData().length - 1);
		setOutput(getData());
	}

	private void recursiveCall(int start, int end) {
		int length = end - start + 1;
		if (length < 2)
			return;
		int pivot = random.nextInt(length) + start;
		swap(start, pivot);
		pivot = partition(start, length);
		recursiveCall(pivot + 1, end);
		recursiveCall(start, pivot - 1);
	}

	private void swap(int start, int pivot) {
		int[] array = getData();
		int temp = array[start];
		array[start] = array[pivot];
		array[pivot] = temp;
	}

	private int partition(int start, int length) {
		int[] array = getData();
		int pivotItem = array[start];
		int afterSmaller = start + 1;
		int afterBigger = afterSmaller;
		int afterLastIndex = start + length;
		while (afterBigger != afterLastIndex) {
			if (array[afterBigger] < pivotItem) {
				swap(afterSmaller, afterBigger);
				afterSmaller++;
			}
			afterBigger++;
		}
		swap(afterSmaller - 1, start);
		return afterSmaller - 1;
	}
}
