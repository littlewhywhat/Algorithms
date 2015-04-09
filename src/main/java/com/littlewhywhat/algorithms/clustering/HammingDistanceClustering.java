package com.littlewhywhat.algorithms.clustering;

import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class HammingDistanceClustering extends
		AbstractAlgorithm<Integer, Data, Integer> {

	final Stack<BinaryString> neighbourhood = new Stack<BinaryString>();

	@Override
	public void execute() {
		int count = 0;
		for (BinaryString string : getData().getList()) {
			if (!isProcessed(string)) {
				count++;
				pushToNeighbourhood(string);
				while (!neighbourhood.isEmpty())
					pushNeighboursOf(neighbourhood.pop());
			}
		}
		setOutput(count);
	}

	private void pushToNeighbourhood(BinaryString string) {
		markProcessed(string);
		neighbourhood.push(string);
	}

	private void markProcessed(BinaryString string) {
		getData().getSet().remove(string);
	}

	private boolean isProcessed(BinaryString string) {
		return !getData().getSet().contains(string);
	}

	private void pushNeighboursOf(BinaryString string) {
		final BinaryString sample = string.copy();
		for (int firstIndex = 0; firstIndex < string.length(); firstIndex++) {
			sample.flip(firstIndex);
			if (!isProcessed(sample))
				pushToNeighbourhood();
			for (int secondIndex = firstIndex + 1; secondIndex < string.length(); secondIndex++) {
				sample.flip(secondIndex);
				if (!isProcessed(sample))
					pushToNeighbourhood(sample.copy());
				sample.flip(secondIndex);
			}
			sample.flip(firstIndex);
		}
	}

}
