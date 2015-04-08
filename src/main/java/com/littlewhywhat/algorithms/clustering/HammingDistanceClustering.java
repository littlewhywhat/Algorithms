package com.littlewhywhat.algorithms.clustering;

import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class HammingDistanceClustering extends
		AbstractAlgorithm<Integer, Data, Integer> {

	final Stack<BinaryString> neighbours = new Stack<BinaryString>();

	@Override
	public void execute() {
		int count = 0;
		for (BinaryString string : getData().getList()) {
			if (!isProcessed(string)) {
				count++;
				pushToNeighbours(string);
				while (!neighbours.isEmpty())
					pushNeighboursOf(neighbours.pop());
			}
		}
		setOutput(count);
	}

	private void pushToNeighbours(BinaryString string) {
		neighbours.push(string);
		getData().getSet().remove(string);
	}

	private boolean isProcessed(BinaryString string) {
		return !getData().getSet().contains(string);
	}

	private void pushToNeighboursIfNotProcessed(BinaryString string) {
		if (!isProcessed(string))
			pushToNeighbours(string);
	}

	private BinaryString getTwoStepNeighbour(boolean[] symbols, int firstIndex, int secondIndex) {
		final boolean[] neighbourSymbols = symbols.clone();
		neighbourSymbols[firstIndex] = !symbols[firstIndex];
		neighbourSymbols[secondIndex] = !symbols[secondIndex];
		return new BinaryString(neighbourSymbols);
	}	

	private BinaryString getOneStepNeighbour(boolean[] symbols, int index) {
		final boolean[] neighbourSymbols = symbols.clone();
		neighbourSymbols[index] = !symbols[index];
		return new BinaryString(neighbourSymbols);
	}

	private void pushNeighboursOf(BinaryString string) {
		final boolean[] symbols = string.getSymbols();
		for (int firstIndex = 0; firstIndex < symbols.length; firstIndex++) {
			for (int secondIndex = firstIndex + 1; secondIndex < symbols.length; secondIndex++) {
				BinaryString neighbourSymbols = getTwoStepNeighbour(symbols, firstIndex, secondIndex);
				pushToNeighboursIfNotProcessed(neighbourSymbols);
			}
			BinaryString neighbourSymbols = getOneStepNeighbour(symbols, firstIndex);
			pushToNeighboursIfNotProcessed(neighbourSymbols);
		}
	}

}
