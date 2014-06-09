package com.littlewhywhat.algorithms.graphs.search;

import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.Vertice;

public class DepthFirstSearch extends
		AbstractAlgorithm<Void, SearchGraph, SearchGraph> {

	private final Stack<Vertice> stack = new Stack<Vertice>();

	@Override
	public void execute() {
		SearchGraph graph = getData();
		for (int i = 0; i < graph.size(); i++) {
			Vertice startVertice = graph.getVertice(i);
			if (!startVertice.isExplored()) {
				stack.push(startVertice);
				while (!stack.empty())
					recursiveCall(graph, stack.pop());
			}
		}
		setOutput(graph);
	}

	protected void recursiveCall(SearchGraph graph, Vertice vertice) {
		if (vertice.isExplored()) {
			//System.out.println(vertice);
			return;
		} else {
			vertice.markAsExplored();
			stack.push(vertice);

			for (com.littlewhywhat.algorithms.graphs.Vertice connection : vertice.getConnections()) {
				Vertice vConnection = (Vertice) connection;
				if (!vConnection.isExplored())
					stack.push(vConnection);
			}
		}
	}

}
