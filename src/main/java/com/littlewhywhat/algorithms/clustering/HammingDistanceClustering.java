package com.littlewhywhat.algorithms.clustering;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class HammingDistanceClustering extends
		AbstractAlgorithm<Integer, Map<HammingDistance, Boolean>, Integer> {

	final Stack<HammingDistance> neighbours = new Stack<HammingDistance>();

	@Override
	public void execute() {
		final Map<HammingDistance, Boolean> codesMap = getData();
		final Collection<HammingDistance> codes = codesMap.keySet();
		int count = 0;
		for (HammingDistance code : codes) {
			if (!codesMap.get(code)) {
				count++;
				pushToNeighbours(code);
				while (!neighbours.isEmpty()) {
					HammingDistance node = neighbours.pop();
					pushNeighboursOf(node);
				}
			}
		}
		setOutput(count);
	}

	private void pushToNeighbours(HammingDistance node) {
		neighbours.push(node);
		getData().put(node, true);
	}

	private boolean isProcessed(HammingDistance node) {
		return getData().get(node);
	}

	private void pushToNeighboursIfNotProcessed(HammingDistance node) {
		if (getData().containsKey(node)
				&& !isProcessed(node))
			pushToNeighbours(node);
	}

	private HammingDistance getTwoStepNeighbour(boolean[] distance, int firstIndex, int secondIndex) {
		final boolean[] neighbour = distance.clone();
		neighbour[firstIndex] = !distance[firstIndex];
		neighbour[secondIndex] = !distance[secondIndex];
		return new HammingDistance(neighbour);
	}	

	private HammingDistance getOneStepNeighbour(boolean[] distance, int index) {
		final boolean[] neighbour = distance.clone();
		neighbour[index] = !distance[index];
		return new HammingDistance(neighbour);
	}

	private void pushNeighboursOf(HammingDistance node) {
		final boolean[] distance = node.getDistance();
		for (int firstIndex = 0; firstIndex < distance.length; firstIndex++) {
			for (int secondIndex = firstIndex + 1; secondIndex < distance.length; secondIndex++) {
				HammingDistance neighbourDistance = getTwoStepNeighbour(distance, firstIndex, secondIndex);
				pushToNeighboursIfNotProcessed(neighbourDistance);
			}
			HammingDistance neighbourDistance = getOneStepNeighbour(distance, firstIndex);
			pushToNeighboursIfNotProcessed(neighbourDistance);
		}
	}

}
