package com.littlewhywhat.algorithms.graphs.search;

import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Edge;
import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Id;

public class DepthFirstSearch<I, T extends Id<I> & SearchItem> extends
		AbstractAlgorithm<Void, Graph<I, T, Edge<I,T>>, Graph<I, T, Edge<I,T>>> {

	private final Stack<T> stack = new Stack<T>();

	@Override
	public void execute() {
		final Graph<I, T, Edge<I,T>> graph = getData();
		for (T vertice : graph) {
			if (!vertice.isExplored()) {
				addToStack(vertice);
				while (!stack.empty())
					recursiveCall(graph, stack.pop());
			}
		}
		setOutput(graph);
	}

	private void addToStack(T vertice) {
		vertice.makeExplored();
		stack.push(vertice);
	}
	
	protected void recursiveCall(Graph<I, T, Edge<I,T>> graph, T start) {
			for (Edge<I,T> edge : graph.getOut(start)) {
				T end =  edge.getEnd();
				if (!end.isExplored())
					addToStack(end);
			}
	}

}
