package com.littlewhywhat.algorithms.graphs.search;

import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.SearchVertice;

public class DepthFirstSearch extends
		AbstractAlgorithm<Void, SearchGraph, SearchGraph> {

	private final Stack<SearchVertice> stack = new Stack<SearchVertice>();

	@Override
	public void execute() {
		SearchGraph graph = getData();
		for (Vertice vertice : graph) {
			SearchVertice startVertice = (SearchVertice) vertice;
			if (!startVertice.isExplored()) {
				addToStack(startVertice);
				while (!stack.empty())
					recursiveCall(graph, stack.pop());
			}
		}
		setOutput(graph);
	}

	private void addToStack(SearchVertice vertice) {
		vertice.markAsExplored();
		stack.push(vertice);
	}
	
	protected void recursiveCall(SearchGraph graph, SearchVertice vertice) {
			for (Vertice connection : graph.getConnections(vertice)) {
				SearchVertice vConnection = (SearchVertice) connection;
				if (!vConnection.isExplored())
					addToStack(vConnection);
			}
	}

}
