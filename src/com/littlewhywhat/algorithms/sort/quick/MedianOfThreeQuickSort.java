package com.littlewhywhat.algorithms.sort.quick;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;

public class MedianOfThreeQuickSort extends AbstractQuickSort {

	private ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<Integer>(new Comparator<Integer>() {

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
		
	});
	
	@Override
	protected int getPivot(int start, int length) {
		int end = start + length - 1;
		int middle = getMiddle(start, length);
		
		set.add(start);
		set.add(end);
		set.add(middle);
		set.pollFirst();
		int ret = 0;
			ret = set.pollFirst();

		set.pollFirst();
		return ret;
	}

	private int getMiddle(int start, int length) {
		if (length % 2 == 0)
			return start + length / 2 - 1;
		return start + length/2;
	}

}
