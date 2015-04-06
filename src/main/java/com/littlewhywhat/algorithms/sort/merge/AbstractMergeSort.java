package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public abstract class AbstractMergeSort extends
		AbstractAlgorithm<Void, int[], int[]> {

	@Override
	public void execute() {
		setup();
		recursiveCall(0, getOutput().length);
	}

	protected void setup() {
		setOutput(getData().clone());
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
			merge(firstHalfStart, secondHalfStart,
					secondHalfLength);
		}
	}

	protected abstract void merge(int firstHalfStart,
			int secondHalfStart, int secondHalfLength);

}
