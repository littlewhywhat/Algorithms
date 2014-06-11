package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.Random;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.ContractionGraph.ContractionVertice;

public class RandomContraction extends
		AbstractAlgorithm<Void, ContractionGraph, Integer> {

	private Random random = new Random();

	@Override
	public void execute() {
		ContractionGraph graph = getData();
		int end = graph.size() - 2;
		for (int i = 0; i < end; i++) {
			ContractionVertice randomStart = getRandomVertice();
			ContractionVertice randomEnd = getRandomConnection(randomStart);
			graph.merge(randomStart, randomEnd);
		}
		setOutput(graph.getVertice(0).sizeConnections());
	}

	private ContractionVertice getRandomConnection(Vertice vertice) {
		return (ContractionVertice) vertice.getConnection(random
				.nextInt(vertice.sizeConnections()));
	}

	private ContractionVertice getRandomVertice() {
		return (ContractionVertice) getData().getVertice(
				random.nextInt(getData().size()));
	}

}
