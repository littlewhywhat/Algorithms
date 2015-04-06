<<<<<<< HEAD:src/com/littlewhywhat/algorithms/graphs/csc/KosarajuAlgo.java
package com.littlewhywhat.algorithms.graphs.csc;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.DirectedGraph;
import com.littlewhywhat.algorithms.graphs.Edge;
import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Id;
import com.littlewhywhat.algorithms.graphs.search.SearchItem;

public class KosarajuAlgo<I, T extends Id<I> & SearchItem>
		extends
		AbstractAlgorithm<Void, DirectedGraph<I, T, Edge<I, T>>, List<Integer>> {

	private LinkedList<Integer> counts = new LinkedList<Integer>();
	private FirstPass firstPass = new FirstPass();
	private LinkedList<I> order = new LinkedList<I>();
	private SecondPass secondPass = new SecondPass();

	@Override
	public void execute() {
		// getData().reverse();
		firstPass.setData(getData());
		firstPass.execute();
		// System.out.println(order);
		getData().reverse();
		reset(getData());
		secondPass.setData(getData());
		secondPass.execute();
		setOutput(counts);
	}

	private void reset(DirectedGraph<I, T, Edge<I, T>> data) {
		// TODO Auto-generated method stub
		
	}

	private class SecondPass
			extends
			AbstractAlgorithm<Void, Graph<I, T, Edge<I, T>>, Graph<I, T, Edge<I, T>>> {

		private final Stack<T> stack = new Stack<T>();

		protected void recursiveCall(Graph<I, T, Edge<I, T>> graph, T start) {
			start.makeExplored();
			for (Edge<I, T> edge : graph.getOut(start)) {
				T end = edge.getEnd();
				if (!end.isExplored())
					stack.push(end);
			}
		}

		@Override
		public void execute() {
			final Graph<I, T, Edge<I, T>> graph = getData();
			for (I index : order) {
				T start = graph.get(index);
				if (!start.isExplored()) {
					stack.push(start);
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

	private class FirstPass extends
	AbstractAlgorithm<Void, Graph<I, T, Edge<I, T>>, Graph<I, T, Edge<I, T>>> {

		private final Stack<Object> stack = new Stack<Object>();

		protected void recursiveCall(Graph<I, T, Edge<I,T>> graph, T start) {
			if (!start.isExplored()) {
				start.makeExplored();
				stack.push(start.getId());

				for (Edge<I,T> edge: graph.getOut(start)) {
					T end = edge.getEnd();
					if (!end.isExplored())
						stack.push(end);
				}
			}
		}

		@Override
		public void execute() {
			final Graph<I, T, Edge<I,T>> graph = getData();
			for (T start : graph) {
				if (!start.isExplored()) {
					stack.push(start);
					while (!stack.empty()) {
						if (stack.peek() instanceof Id<?>)
							recursiveCall(graph, (T)stack.pop());
						else
							order.addFirst((I)stack.pop());
					}
				}
			}
			setOutput(graph);
		}

	}

}
=======
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
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/main/java/com/littlewhywhat/algorithms/graphs/csc/KosarajuAlgo.java
