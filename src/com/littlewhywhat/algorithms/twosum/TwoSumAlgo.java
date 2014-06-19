package com.littlewhywhat.algorithms.twosum;

import java.util.HashSet;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class TwoSumAlgo extends
		AbstractAlgorithm<TwoSumConfig, int[], Integer> {

	private final HashSet<Integer> hashSet = new HashSet<Integer>();
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		initHashSet();
		int count = 0;
		final int upperBound = getConfig().getUpperBound();
		final int lowerBound = getConfig().getLowerBound();
		for (int integer : getData()) {
			int result;
			hashSet.remove(integer);
			for (int inBorders = lowerBound; inBorders <= upperBound; inBorders++) {
				result = lowerBound - integer;
				if (hashSet.contains(result) && !hashSet.contains(integer))
					count++;
			}
		}
		setOutput(count);
	}

	private void initHashSet() {
		for (int integer : getData())
			hashSet.add(integer);
	}

}
