package com.littlewhywhat.algorithms.graphs.csc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.search.DepthFirstSearch;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph;
import com.littlewhywhat.algorithms.graphs.search.SearchGraph.SearchVertice;

public class KosarajuAlgo extends
		AbstractAlgorithm<Void, KosarajuGraphs, LinkedList<Integer>> {

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

		private final Stack<SearchVertice> stack = new Stack<SearchVertice>();

		@Override
		protected void recursiveCall(SearchGraph graph, SearchVertice vertice) {
			vertice.markAsExplored();
			for (com.littlewhywhat.algorithms.graphs.Vertice connection : vertice.getConnections()) {
				SearchVertice mConnection = (SearchVertice) connection;
				if (!mConnection.isExplored())
					stack.push(mConnection);
			}
		}

		@Override
		public void execute() {
			SearchGraph graph = getData();
			for (int i = order.size() - 1; i > -1; i--) {
				SearchVertice startVertice = (SearchVertice) graph.getVertice(order.get(i));
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

				for (com.littlewhywhat.algorithms.graphs.Vertice connection : vertice.getConnections()) {
					SearchVertice mConnection = (SearchVertice) connection;
					if (!mConnection.isExplored())
						stack.push(mConnection);
				}
			}
		}

		@Override
		public void execute() {
			SearchGraph graph = getData();
			for (int i = 0; i < graph.size(); i++) {
				SearchVertice startVertice = (SearchVertice) graph.getVertice(i);
				if (!startVertice.isExplored()) {
					stack.push(startVertice);
					while (!stack.empty()) {
						if (stack.peek() instanceof SearchVertice)
							recursiveCall(graph, (SearchVertice) stack.pop());
						else
							order.add((Integer) stack.pop());
					}
				}
			}
			setOutput(graph);
		}

	}

}
