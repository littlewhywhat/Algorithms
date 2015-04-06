package com.littlewhywhat.algorithms.graphs.mincut.contraction;

<<<<<<< HEAD:src/com/littlewhywhat/algorithms/graphs/mincut/contraction/RandomContraction.java
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
=======
import java.util.LinkedList;
import java.util.Random;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.ContractionGraph.ContractionVertice;

public class RandomContraction extends
		AbstractAlgorithm<Void, ContractionGraph, Integer> {

	private Random random = new Random();

	@Override
	public void execute() {
		ContractionGraph graph = getData();
		final LinkedList<Vertice> vertices = getVertices(graph);
		while (graph.size() != 2) {
			ContractionVertice randomStart = getRandomVertice(vertices);
			ContractionVertice randomEnd = getRandomConnection(graph, randomStart);
			graph.merge(randomStart, randomEnd);
		}
		setOutput(graph.getConnections(vertices.peek()).size());
	}

	private ContractionVertice getRandomVertice(LinkedList<Vertice> vertices) {
		ContractionVertice vertice = (ContractionVertice) vertices.get(random.nextInt(vertices.size()));
		vertices.remove(vertice);
		return vertice;
	}

	private LinkedList<Vertice> getVertices(ContractionGraph graph) {
		final LinkedList<Vertice> list = new LinkedList<Vertice>();
		for (Vertice vertice: graph)
			list.add(vertice);
		return list;
	}

	private ContractionVertice getRandomConnection(Graph graph, Vertice vertice) {
		return (ContractionVertice) ((ContractionGraph) graph).getConnections(vertice).get(random
				.nextInt(((ContractionGraph) graph).getConnections(vertice).size())).getVertice();
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/main/java/com/littlewhywhat/algorithms/graphs/mincut/contraction/RandomContraction.java
	}

}
