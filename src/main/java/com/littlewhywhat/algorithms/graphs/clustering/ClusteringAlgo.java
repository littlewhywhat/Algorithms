package com.littlewhywhat.algorithms.graphs.clustering;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.clustering.UnionFindGraph.Group;
import com.littlewhywhat.algorithms.graphs.clustering.UnionFindGraph.WeightedEdge;
import com.littlewhywhat.datastructure.Heap;

public class ClusteringAlgo extends
		AbstractAlgorithm<Integer, UnionFindGraph, Integer> {

	@Override
	public void execute() {
		final UnionFindGraph graph = getData();
		final Heap<WeightedEdge> heapMin = graph.getMinHeap();
		while (heapMin.size() != 0 && graph.getGroups().size() > getConfig()) {
			WeightedEdge edge = heapMin.poll();
			Group one = graph.find(edge.getStart());
			Group two = graph.find(edge.getVertice());
			if (!one.equals(two))
				graph.union(one, two);
		}
		WeightedEdge edge;
		Group one;
		Group two;
		do {
			edge = heapMin.poll();
			one = graph.find(edge.getStart());
			two = graph.find(edge.getVertice());
		} while (one.equals(two));
		setOutput(edge.getWeight());
	}

}
