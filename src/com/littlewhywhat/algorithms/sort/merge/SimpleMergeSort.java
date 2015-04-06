package com.littlewhywhat.algorithms.sort.merge;

public class SimpleMergeSort extends AbstractMergeSort {

	private long countInversions = 0;
	
	public long getInversions() {
		return countInversions;
	}
	
	@Override
	protected void merge(int firstHalfStart,
			int secondHalfStart, int secondHalfLength) {
		int firstHalfLength = secondHalfStart - firstHalfStart;
		int[] data = getOutput();
		int[] result = getData();
		int i = firstHalfStart;
		int start = firstHalfStart;
		int end = secondHalfStart + secondHalfLength;
		
		while (secondHalfLength != 0 && firstHalfLength != 0) {
			if (data[firstHalfStart] < data[secondHalfStart]) {
				result[i] = data[firstHalfStart];
				firstHalfStart++;
				firstHalfLength--;
			} else {
				result[i] = data[secondHalfStart];
				secondHalfStart++;
				secondHalfLength--;
				countInversions += firstHalfLength;
			}
			i++;
		}
		if (i < end)
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
		for (i = start; i < end  ; i++)
			data[i] = result[i];
		
	}

}
