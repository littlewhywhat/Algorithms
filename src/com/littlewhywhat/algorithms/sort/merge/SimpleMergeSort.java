package com.littlewhywhat.algorithms.sort.merge;

public class SimpleMergeSort extends AbstractMergeSort {

	@Override
	protected void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		int[] data = getOutput();
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
