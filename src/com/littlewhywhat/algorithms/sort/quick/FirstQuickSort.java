package com.littlewhywhat.algorithms.sort.quick;

public class FirstQuickSort extends AbstractQuickSort {

	@Override
	protected int getPivot(int start, int length) {		
		return start;
	}

}
