package com.littlewhywhat.algorithms.sort;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class InsertionSort extends
		AbstractAlgorithm<int[], int[], int[]> {

	@Override
	public void execute() {
		int start = getConfig()[0];
		int end = getConfig()[1];
		int[] array = getData();
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

}
