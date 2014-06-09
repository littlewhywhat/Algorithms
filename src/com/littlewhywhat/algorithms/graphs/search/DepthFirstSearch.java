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
		for (int i = 0; i < graph.size(); i++) {
			SearchVertice startVertice = (SearchVertice) graph.getVertice(i);
			if (!startVertice.isExplored()) {
				stack.push(startVertice);
				while (!stack.empty())
					recursiveCall(graph, stack.pop());
			}
		}
		setOutput(graph);
	}

	protected void recursiveCall(SearchGraph graph, SearchVertice vertice) {
		if (vertice.isExplored()) {
			//System.out.println(vertice);
			return;
		} else {
			vertice.markAsExplored();
			stack.push(vertice);

			for (Vertice connection : vertice.getConnections()) {
				SearchVertice vConnection = (SearchVertice) connection;
				if (!vConnection.isExplored())
					stack.push(vConnection);
			}
		}
	}

}
