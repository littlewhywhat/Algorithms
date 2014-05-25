package com.littlewhywhat.algorithms.sort.quick;

public class LastQuickSort extends AbstractQuickSort {

	@Override
	protected int getPivot(int start, int length) {
		return start + length - 1;
	}

}
