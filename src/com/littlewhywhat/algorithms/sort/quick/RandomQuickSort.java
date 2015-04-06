package com.littlewhywhat.algorithms.sort.quick;

import java.util.Random;

public class RandomQuickSort extends AbstractQuickSort {

	private Random random = new Random();
	
	@Override
	protected int getPivot(int start, int length) {
		return random.nextInt(length) + start;
	}

}
