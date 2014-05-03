package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public abstract class AbstractMergeSort extends
		AbstractAlgorithm<Void, int[], int[]> {

	@Override
	public void execute() {
		setOutput(getData().clone());
		recursiveCall(0, getOutput().length);
	}

	private void recursiveCall(int first, int length) {
		if (length == 1)
			return;
		else {
			int firstHalfStart = first;
			int firstHalfLength = length / 2;
			int secondHalfStart = first + length / 2;
			int secondHalfLength = length - firstHalfLength;
			recursiveCall(firstHalfStart, firstHalfLength);
			recursiveCall(secondHalfStart, secondHalfLength);
			merge(firstHalfStart, firstHalfLength, secondHalfStart,
					secondHalfLength);
		}
	}

	protected abstract void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength);

}
