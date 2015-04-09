package com.littlewhywhat.algorithms.clustering;

import java.util.Map;
import java.util.List;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class HammingDistanceClustering extends
		AbstractAlgorithm<Integer, Data, Integer> {

	private final Stack<BinaryString> neighbourhood = new Stack<BinaryString>();

	@Override
	public void execute() {
		int count = 0;
		for (BinaryString string : getData().getList()) {
			BinaryString neighbour = getMap().remove(string);
			if (neighbour != null) {
				count++;
				neighbourhood.push(neighbour);
				while (!neighbourhood.isEmpty())
					pushNeighboursOf(neighbourhood.pop());
			}
		}
		setOutput(count);
	}

	private void extrudeAndPush(BinaryString pattern) {
	 	final BinaryString neighbour = getMap().remove(pattern);
		if (neighbour != null)
			neighbourhood.push(neighbour);
	}

	private Map<BinaryString, BinaryString> getMap() {
		return getData().getMap();
	}

	private void pushNeighboursOf(BinaryString string) {
		final BinaryString pattern = string.copy();
		for (int firstIndex = 0; firstIndex < string.length(); firstIndex++) {
			pattern.flip(firstIndex);
			extrudeAndPush(pattern);
			for (int secondIndex = firstIndex + 1; secondIndex < string.length(); secondIndex++) {
				pattern.flip(secondIndex);
				extrudeAndPush(pattern);
				pattern.flip(secondIndex);
			}
			pattern.flip(firstIndex);
		}
	}

}
