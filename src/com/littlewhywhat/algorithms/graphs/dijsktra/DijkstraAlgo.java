package com.littlewhywhat.algorithms.graphs.dijsktra;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Id;
import com.littlewhywhat.algorithms.graphs.WeightedEdge;
import com.littlewhywhat.algorithms.graphs.search.SearchItem;
import com.littlewhywhat.datastructure.Heap;
import com.littlewhywhat.datastructure.SimpleHeap;

public class DijkstraAlgo<I, T extends Id<I> & Station & SearchItem, E extends WeightedEdge<I, T>>
		extends AbstractAlgorithm<I, Graph<I, T, E>, int[]> {

	private static final int UNREACHABLE = Integer.MAX_VALUE;
	private static final int REACHED = 0;

	private final Comparator<T> comparator = new Comparator<T>() {

		@Override
		public int compare(T one, T two) {
			final Integer oneDistance = one.getDistanceToSource();
			final Integer twoDistance = two.getDistanceToSource();
			return oneDistance.compareTo(twoDistance);
		}

	};

	private final Heap<T> unprocessed = SimpleHeap.getMinHeap(comparator);
	private final Heap<T> itemsToUpdate = SimpleHeap.getMinHeap(comparator);
	private final Stack<T> stack = new Stack<T>();

	@Override
	public void execute() {
		final Graph<I, T, E> graph = getData();
		final T source = graph.get(getConfig());
		addItemsToHeap(graph, source);
		T station;
		while (unprocessed.size() != 0) {
			station = (T) unprocessed.poll();
			station.makeExplored();
			insertItemsToUpdate(graph.getOut(station));
			updateHeap(graph);
		}

	}

	private void insertItemsToUpdate(List<E> edges) {
		for (E edge : edges) {
			T itemToUpdate = edge.getEnd();
			if (!itemToUpdate.isExplored()) {
				int newDistance = edge.getStart().getDistanceToSource() + edge.getWeight();
				if (itemToUpdate.getDistanceToSource() > newDistance) {
					itemToUpdate.setDistanceToSource(newDistance);
					itemsToUpdate.insert(itemToUpdate);
				}
			}
		}
	}
	
	private void updateHeap(Graph<I, T, E> graph) {
		while (itemsToUpdate.size() != 0) {
			do {
				stack.push(unprocessed.poll());
			} while (!itemsToUpdate.peek().getId().equals(stack.peek().getId()));
			itemsToUpdate.poll();
		}
		while (!stack.isEmpty())
			unprocessed.insert(stack.pop());
	}

	private void addItemsToHeap(Graph<I, T, E> graph, T source) {
		source.setDistanceToSource(REACHED);
		unprocessed.insert(source);
		for (T item : graph)
			if (!item.equals(source)) {
				item.setDistanceToSource(UNREACHABLE);
				unprocessed.insert(item);
			}
	}

}
