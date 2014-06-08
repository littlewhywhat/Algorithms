package com.littlewhywhat.algorithms.graphs.search;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.Vertice;

public class DepthFirstSearch extends
		AbstractAlgorithm<Void, SearchGraph, SearchGraph> {

	@Override
	public void execute() {
		SearchGraph graph = getData();
		Vertice startVertice = graph.getVertice(0);
		recursiveCall(graph, startVertice);
		setOutput(graph);
	}

	private void recursiveCall(SearchGraph graph, Vertice startVertice) {
		startVertice.markAsExplored();
		for (Vertice vertice : startVertice.getConnections())
			if (!vertice.isExplored())
				recursiveCall(graph, vertice);
	}
	
}
