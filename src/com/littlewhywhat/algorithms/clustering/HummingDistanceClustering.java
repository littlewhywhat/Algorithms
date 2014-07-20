package com.littlewhywhat.algorithms.clustering;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class HummingDistanceClustering extends
		AbstractAlgorithm<Integer, Map<HummingDistance, Boolean>, Integer> {

	final Stack<HummingDistance> stack = new Stack<HummingDistance>();
	final Stack<HummingDistance> buffer = new Stack<HummingDistance>();

	@Override
	public void execute() {
		final Map<HummingDistance, Boolean> codesMap = getData();
		final Collection<HummingDistance> codes = codesMap.keySet();
		int count = 0;
		for (HummingDistance code : codes) {
			if (!codesMap.get(code)) {
				count++;
				pushToStack(code);
				//System.out.println("Group " + count + ":");
				while (!stack.isEmpty()) {
					HummingDistance node = stack.pop();
					//System.out.println(node);
					fillBufferWithPossibleNeighbours(node);
					//System.out.println(buffer);
					while (!buffer.isEmpty()) {
						HummingDistance neighbour = buffer.pop();
						if (codesMap.containsKey(neighbour)
								&& !codesMap.get(neighbour))
							pushToStack(neighbour);
					}
				}
			}
		}
		setOutput(count);
	}

	private void pushToStack(HummingDistance node) {
		stack.push(node);
		getData().put(node, true);
	}
	

	private void fillBufferWithPossibleNeighbours(HummingDistance node) {
		boolean[] distance = node.getDistance();
		for (int firstIndex = 0; firstIndex < distance.length - 1; firstIndex++) {
			for (int secondIndex = firstIndex + 1; secondIndex < distance.length; secondIndex++) {
				boolean[] neighbour = new boolean[distance.length];
				for (int i = 0; i < neighbour.length; i++)
					neighbour[i] = distance[i];
				neighbour[firstIndex] = !distance[firstIndex];
				neighbour[secondIndex] = !distance[secondIndex];
				buffer.push(new HummingDistance(neighbour));
			}

		}
		for (int firstIndex = 0; firstIndex < distance.length; firstIndex++) {
			boolean[] neighbour = distance.clone();
			neighbour[firstIndex] = !distance[firstIndex];
			buffer.push(new HummingDistance(neighbour));
		}
	}

}
