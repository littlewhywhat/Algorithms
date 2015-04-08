package com.littlewhywhat.algorithms.clustering;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class HummingDistanceClustering extends
		AbstractAlgorithm<Integer, Map<HummingDistance, Boolean>, Integer> {

	final Stack<HummingDistance> neighbours = new Stack<HummingDistance>();

	@Override
	public void execute() {
		final Map<HummingDistance, Boolean> codesMap = getData();
		final Collection<HummingDistance> codes = codesMap.keySet();
		int count = 0;
		for (HummingDistance code : codes) {
			if (!codesMap.get(code)) {
				count++;
				pushToNeighbours(code);
				while (!neighbours.isEmpty()) {
					HummingDistance node = neighbours.pop();
					pushNeighboursOf(node);
				}
			}
		}
		setOutput(count);
	}

	private void pushToNeighbours(HummingDistance node) {
		neighbours.push(node);
		getData().put(node, true);
	}

	private boolean isProcessed(HummingDistance node) {
		return getData().get(node);
	}

	private void pushToNeighboursIfNotProcessed(HummingDistance node) {
		if (getData().containsKey(node)
				&& !isProcessed(node))
			pushToNeighbours(node);
	}

	private HummingDistance getTwoStepNeighbour(boolean[] distance, int firstIndex, int secondIndex) {
		final boolean[] neighbour = distance.clone();
		neighbour[firstIndex] = !distance[firstIndex];
		neighbour[secondIndex] = !distance[secondIndex];
		return new HummingDistance(neighbour);
	}	

	private HummingDistance getOneStepNeighbour(boolean[] distance, int index) {
		final boolean[] neighbour = distance.clone();
		neighbour[index] = !distance[index];
		return new HummingDistance(neighbour);
	}

	private void pushNeighboursOf(HummingDistance node) {
		final boolean[] distance = node.getDistance();
		for (int firstIndex = 0; firstIndex < distance.length; firstIndex++) {
			for (int secondIndex = firstIndex + 1; secondIndex < distance.length; secondIndex++) {
				HummingDistance neighbourDistance = getTwoStepNeighbour(distance, firstIndex, secondIndex);
				pushToNeighboursIfNotProcessed(neighbourDistance);
			}
			HummingDistance neighbourDistance = getOneStepNeighbour(distance, firstIndex);
			pushToNeighboursIfNotProcessed(neighbourDistance);
		}
	}

}
