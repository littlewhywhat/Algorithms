package com.littlewhywhat.algorithms.graphs.search;

import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
<<<<<<< HEAD:src/com/littlewhywhat/algorithms/graphs/search/DepthFirstSearch.java
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
=======
import com.littlewhywhat.algorithms.graphs.Connection;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.SearchVertice;

public class DepthFirstSearch extends
		AbstractAlgorithm<Void, SearchGraph, SearchGraph> {

	private final Stack<SearchVertice> stack = new Stack<SearchVertice>();

	@Override
	public void execute() {
		final SearchGraph graph = getData();
		SearchVertice startVertice;
		for (Vertice vertice : graph) {
			startVertice = (SearchVertice) vertice;
			if (!startVertice.isExplored()) {
				addToStack(startVertice);
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/main/java/com/littlewhywhat/algorithms/graphs/search/DepthFirstSearch.java
				while (!stack.empty())
					recursiveCall(graph, stack.pop());
			}
		}
		setOutput(graph);
	}

<<<<<<< HEAD:src/com/littlewhywhat/algorithms/graphs/search/DepthFirstSearch.java
	private void addToStack(T vertice) {
		vertice.makeExplored();
		stack.push(vertice);
	}
	
	protected void recursiveCall(Graph<I, T, Edge<I,T>> graph, T start) {
			for (Edge<I,T> edge : graph.getOut(start)) {
				T end =  edge.getEnd();
				if (!end.isExplored())
					addToStack(end);
=======
	private void addToStack(SearchVertice vertice) {
		vertice.markAsExplored();
		stack.push(vertice);
	}
	
	protected void recursiveCall(SearchGraph graph, SearchVertice vertice) {
			for (Connection connection : graph.getConnections(vertice)) {
				SearchVertice vConnection = (SearchVertice) connection.getVertice();
				if (!vConnection.isExplored())
					addToStack(vConnection);
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/main/java/com/littlewhywhat/algorithms/graphs/search/DepthFirstSearch.java
			}
	}

}
