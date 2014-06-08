package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.Random;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.ContractionGraph.Vertice;

public class RandomContraction extends AbstractAlgorithm<Void, ContractionGraph, Integer> {

	private Random random = new Random();

	@Override
	public void execute() {
		ContractionGraph graph = getData();
		int end = graph.size() - 2;
		for (int i = 0; i < end; i++) {
			Vertice randomStart = getRandomVertice();
			Vertice randomEnd = getRandomConnection(randomStart);
			graph.merge(randomStart, randomEnd);
		}
		setOutput(graph.getVertice(0).connectionsCount());
	}

	private Vertice getRandomConnection(Vertice vertice) {
		return vertice
				.getConnection(random.nextInt(vertice.connectionsCount()));
	}

	private Vertice getRandomVertice() {
		return getData().getVertice(random.nextInt(getData().size()));
	}

}
