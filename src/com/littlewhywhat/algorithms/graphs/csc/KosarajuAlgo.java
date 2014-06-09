package com.littlewhywhat.algorithms.graphs.csc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.search.DepthFirstSearch;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.Vertice;

public class KosarajuAlgo extends
		AbstractAlgorithm<Void, ReversibleGraph, LinkedList<Integer>> {

	private LinkedList<Integer> counts = new LinkedList<Integer>();
	private FirstPass firstPass = new FirstPass();
	private ArrayList<Integer> order = new ArrayList<Integer>();
	private SecondPass secondPass = new SecondPass();

	@Override
	public void execute() {
		firstPass.setData(getData().getReversed());
		firstPass.execute();
		System.out.println(order.size());
		secondPass.setData(getData().getGraph());
		secondPass.execute();
		setOutput(counts);
	}

	public class SecondPass extends DepthFirstSearch {

		private final Stack<Vertice> stack = new Stack<Vertice>();

		@Override
		protected void recursiveCall(SearchGraph graph, Vertice vertice) {
			vertice.markAsExplored();
			for (Vertice connection : vertice.getConnections())
				if (!connection.isExplored())
					stack.push(connection);
		}

		@Override
		public void execute() {
			SearchGraph graph = getData();
			for (int i = order.size() - 1; i > -1; i--) {
				Vertice startVertice = graph.getVertice(order.get(i));
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
		protected void recursiveCall(SearchGraph graph, Vertice vertice) {
			if (!vertice.isExplored()) {
				vertice.markAsExplored();
				stack.push(vertice.getIndex());

				for (Vertice connection : vertice.getConnections())
					if (!connection.isExplored())
						stack.push(connection);
			}
		}

		@Override
		public void execute() {
			SearchGraph graph = getData();
			for (int i = 0; i < graph.size(); i++) {
				Vertice startVertice = graph.getVertice(i);
				if (!startVertice.isExplored()) {
					stack.push(startVertice);
					while (!stack.empty()) {
						if (stack.peek() instanceof Vertice)
							recursiveCall(graph, (Vertice) stack.pop());
						else
							order.add((Integer) stack.pop());
					}
				}
			}
			setOutput(graph);
		}

	}

}
