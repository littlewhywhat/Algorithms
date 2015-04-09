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
		neighbourhood.push(string);
		getData().getSet().remove(string);
	}

	private boolean isProcessed(BinaryString string) {
		return !getData().getSet().contains(string);
	}

	private void pushToNeighbourhoodIfNotProcessed(BinaryString string) {
		if (!isProcessed(string))
			pushToNeighbourhood(string);
	}

	private BinaryString getTwoStepNeighbour(BinaryString string, int firstIndex, int secondIndex) {
		final boolean[] neighbourSymbols = string.getSymbols().clone();
		neighbourSymbols[firstIndex] = !string.getSymbols()[firstIndex];
		neighbourSymbols[secondIndex] = !string.getSymbols()[secondIndex];
		return new BinaryString(neighbourSymbols);
	}	

	private BinaryString getOneStepNeighbour(BinaryString string, int index) {
		final boolean[] neighbourSymbols = string.getSymbols().clone();
		neighbourSymbols[index] = !string.getSymbols()[index];
		return new BinaryString(neighbourSymbols);
	}

	private void pushNeighboursOf(BinaryString string) {
		for (int firstIndex = 0; firstIndex < string.getSymbols().length; firstIndex++) {
			pushToNeighbourhoodIfNotProcessed(getOneStepNeighbour(string, firstIndex));
			for (int secondIndex = firstIndex + 1; secondIndex < string.getSymbols().length; secondIndex++)
				pushToNeighbourhoodIfNotProcessed(getTwoStepNeighbour(string, firstIndex, secondIndex));
		}
	}

}
