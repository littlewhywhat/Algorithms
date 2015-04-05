package com.littlewhywhat.algorithms.graphs.csc;

import java.util.LinkedList;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Connection;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.search.DepthFirstSearch;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.SearchVertice;

public class KosarajuAlgo extends
		AbstractAlgorithm<Void, ReversibleGraph, LinkedList<Integer>> {

	private LinkedList<Integer> counts = new LinkedList<Integer>();
	private FirstPass firstPass = new FirstPass();
	private LinkedList<Integer> order = new LinkedList<Integer>();
	private SecondPass secondPass = new SecondPass();

	@Override
	public void execute() {
		// getData().reverse();
		firstPass.setData(getData());
		firstPass.execute();
		// System.out.println(order);
		getData().reverse();
		getData().reset();
		secondPass.setData(getData());
		secondPass.execute();
		setOutput(counts);
	}

	public class SecondPass extends DepthFirstSearch {

		private final Stack<SearchVertice> stack = new Stack<SearchVertice>();

		@Override
		protected void recursiveCall(SearchGraph graph, SearchVertice vertice) {
			vertice.markAsExplored();
			for (Connection connection : graph.getConnections(vertice)) {
				SearchVertice verticeOther = (SearchVertice) connection.getVertice();
				if (!verticeOther.isExplored())
					stack.push(verticeOther);
			}
		}

		@Override
		public void execute() {
			SearchGraph graph = getData();
			for (Integer index : order) {
				SearchVertice startVertice = (SearchVertice) graph
						.get(index);
				if (!startVertice.isExplored()) {
					stack.push(startVertice);
					int count = 0;
					while (!stack.empty()) {
						if (!stack.peek().isExplored()) {
							count++;

							recursiveCall(graph, stack.pop());
						} else
							stack.pop();
					}
					counts.add(count);
				}
			}
			setOutput(graph);
		}

	}

	public class FirstPass extends DepthFirstSearch {

		private final Stack<Object> stack = new Stack<Object>();

		@Override
		protected void recursiveCall(SearchGraph graph, SearchVertice vertice) {
			if (!vertice.isExplored()) {
				vertice.markAsExplored();
				stack.push(vertice.getIndex());

				for (Connection connection : graph.getConnections(vertice)) {
					SearchVertice verticeOther = (SearchVertice) connection.getVertice();
					if (!verticeOther.isExplored())
						stack.push(verticeOther);
				}
			}
		}

		@Override
		public void execute() {
			SearchGraph graph = getData();
			for (Vertice vertice : graph) {
				SearchVertice startVertice = (SearchVertice) vertice;
				if (!startVertice.isExplored()) {
					stack.push(startVertice);
					while (!stack.empty()) {
						if (stack.peek() instanceof SearchVertice)
							recursiveCall(graph, (SearchVertice) stack.pop());
						else
							order.addFirst((Integer) stack.pop());
					}
				}
			}
			setOutput(graph);
		}

	}

}
