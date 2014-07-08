package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Id;
import com.littlewhywhat.algorithms.graphs.WeightedEdge;

public class DijkstraAlgo<I, T extends Id<I>, E extends WeightedEdge<I,T>> extends AbstractAlgorithm<I, Graph<I, T, E>, int[]> {

	@Override
	public void execute() {
		Graph<I, T, E> graph = getData();
		T source = graph.get(getConfig());
		while (!graph.getOut(source).isEmpty()) {
			
		}

	}

}
