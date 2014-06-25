package com.littlewhywhat.algorithms.graphs.mincut.contraction;

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
		int end = graph.size() - 2;
		for (int i = 0; i < end; i++) {
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
	}

}
