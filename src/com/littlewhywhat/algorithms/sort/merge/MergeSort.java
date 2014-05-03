package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class MergeSort extends AbstractAlgorithm<Void, int[], int[]> {

	@Override
	public void execute() {
		recursiveCall(0, getData().length);
	}

	private void recursiveCall(int first, int length) {
		if (length == 1)
			return;
		else {
			int firstHalfStart = first;
			int firstHalfLength = length / 2;
			int secondHalfStart = first + length / 2;
			int secondHalfLength = length - firstHalfLength;
			recursiveCall(firstHalfStart, firstHalfLength);
			recursiveCall(secondHalfStart, secondHalfLength);
			merge(firstHalfStart, firstHalfLength, secondHalfStart,
					secondHalfLength);
		}
	}

	private void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		int[] data = getData();
		int[] result = new int[firstHalfLength + secondHalfLength];
		int i = 0;
		while (secondHalfLength != 0 && firstHalfLength != 0) {
			if (data[firstHalfStart] < data[secondHalfStart]) {
				result[i] = data[firstHalfStart];
				firstHalfStart++;
				firstHalfLength--;
			} else {
				result[i] = data[secondHalfStart];
				secondHalfStart++;
				secondHalfLength--;
			}
			i++;
		}
		if (i < result.length)
			if (firstHalfLength == 0)
				while (secondHalfLength != 0) {
					result[i] = data[secondHalfStart];
					secondHalfStart++;
					secondHalfLength--;
					i++;
				}
			else
				while (firstHalfLength != 0) {
					result[i] = data[firstHalfStart];
					firstHalfStart++;
					firstHalfLength--;
					i++;
				}
		for (i = result.length ; i > 0; i--)
			data[secondHalfStart - i] = result[result.length - i];
	}
}
