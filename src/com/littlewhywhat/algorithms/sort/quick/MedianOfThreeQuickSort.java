package com.littlewhywhat.algorithms.sort.quick;

import java.util.Comparator;

public class MedianOfThreeQuickSort extends AbstractQuickSort {

	private Comparator<Integer> comparator = new Comparator<Integer>() {

		@Override
		public int compare(Integer one, Integer two) {
			int itemOne = getData()[one];
			int itemTwo = getData()[two];
			if (itemOne == itemTwo)
				return 0;
			if (itemOne > itemTwo)
				return 1;
			return -1;
		}
		
	};
	
	@Override
	protected int getPivot(int start, int length) {
		int end = start + length - 1;
		int middle = getMiddle(start, length);
		int compare = comparator.compare(middle, end) + comparator.compare(middle, start);
		if (compare == 2)
			return comparator.compare(start, end) > 0 ? start : end;
		if (compare == -2)
			return comparator.compare(start, end) < 0 ? start : end;
		return middle;
	}

	private int getMiddle(int start, int length) {
		if (length % 2 == 0)
			return start + length / 2 - 1;
		return start + length/2;
	}

}
