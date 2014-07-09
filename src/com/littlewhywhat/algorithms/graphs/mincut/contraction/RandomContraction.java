package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.List;
import java.util.Random;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Edge;
import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Id;

public class RandomContraction<I, T extends Id<I>> extends
		AbstractAlgorithm<Void, Graph<I, T, Edge<I, T>>, Integer> {

	private final Random random = new Random();

	@Override
	public void execute() {
		final Graph<I, T, Edge<I, T>> graph = getData();
		final List<Edge<I, T>> edges = graph.edges();
		while (graph.size() != 2) {
			Edge<I, T> edge = getRandomEdge(edges);
			merge(graph, edge);
		}
		setOutput(edges.size());
	}

	private void merge(Graph<I, T, Edge<I, T>> graph, Edge<I, T> edge) {
		// TODO Auto-generated method stub
		
	}

	private Edge<I, T> getRandomEdge(List<Edge<I, T>> edges) {
		return edges.get(random.nextInt(edges.size()));
	}

}
