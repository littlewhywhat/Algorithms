package com.littlewhywhat.algorithms.sort;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class InsertionSort extends
		AbstractAlgorithm<Void, int[], int[]> {

	@Override
	public void execute() {
		int[] array = getData();
		for (int j = 1; j < array.length; j++) {
			int newItem = array[j];
			int i = j-1;
			while (i > -1 && newItem < array[i]) {
				array[i+1] = array[i];
				i--;
			}
			array[i+1] = newItem;
		}
		setOutput(array);	
	}

}
