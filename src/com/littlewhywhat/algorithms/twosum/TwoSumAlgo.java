package com.littlewhywhat.algorithms.twosum;

import java.util.HashSet;
import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class TwoSumAlgo extends
		AbstractAlgorithm<TwoSumConfig, List<Long>, Integer> {

	private final HashSet<Long> hashSet = new HashSet<Long>();
	private final HashSet<Integer> resultsSet = new HashSet<Integer>();

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		initHashSet();
		System.out.println();
		final int upperBound = getConfig().getUpperBound();
		final int lowerBound = getConfig().getLowerBound();
		for (long longInteger : getData()) {
			hashSet.remove(longInteger);
			long lowerResultBound = lowerBound - longInteger;
			long upperResultBound = upperBound - longInteger;
			for (long result = lowerResultBound; result <= upperResultBound; result++) {
				if (hashSet.contains(result))
					resultsSet.add((int) (result + longInteger));
			}
		}
		System.out.println(resultsSet.size());
		setOutput(resultsSet.size());
	}

	private void initHashSet() {
		int size = getData().size();
		for (int i = 0; i < size; i++) {
			Long longInteger = getData().get(i);
			if (!hashSet.contains(longInteger))
				hashSet.add(longInteger);
			else {
				getData().remove(i);
				i--;
				size--;
			}
		}
	}

}
