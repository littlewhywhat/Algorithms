package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.LinkedList;
import java.util.Random;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.Graph.Vertice;

public class RandomContraction extends AbstractAlgorithm<Void, Graph, Integer> {

	private Random random = new Random();

	@Override
	public void execute() {
		int end = getData().size() - 2;
		for (int i = 0; i < end; i++) {
			System.out.print("Start ");
			Vertice randomStart = getRandomVertice();
			System.out.println(randomStart);
			System.out.print("End ");
			Vertice randomEnd = getRandomConnection(randomStart);

			System.out.println(randomEnd);
			merge(randomStart, randomEnd);
		}
		setOutput(countEdges());
		System.out.println(getOutput());
	}

	private Integer countEdges() {
		Graph graph = getData();
		for (int i = 0; i < graph.size(); i++) {
			Vertice vertice = graph.getVertice(i);
			if (vertice.getConnections().size() != 0) {
				return vertice.getConnections().size();
			}
		}
		return null;
	}

	private void merge(Vertice randomStart, Vertice randomEnd) {
		randomEnd.setMerged(randomStart);
		for (Vertice vertice : randomEnd.getConnections()) {
			if (vertice.isMerged())
				vertice = vertice.getMerge();
			if (!vertice.equals(randomStart))
				randomStart.addToConnections(vertice);
		}
		randomEnd.getConnections().clear();
		int size = randomStart.getConnections().size();
		for (int i = 0; i < size; i++) {
			Vertice vertice = randomStart.getConnections().pop();
			while (vertice.isMerged())
				vertice = vertice.getMerge();
			if (!vertice.equals(randomEnd) && !vertice.equals(randomStart))
				randomStart.addToConnections(vertice);
		}
	}

	private Vertice getRandomConnection(Vertice vertice) {
		LinkedList<Vertice> connections = vertice.getConnections();
		Vertice connection = connections
				.get(random.nextInt(connections.size()));
		Vertice temp;
		while (connection.isMerged()) {
			System.out.print(connection + ">");
			temp = connection;
			connection = connection.getMerge();
			temp.setMerged(vertice);
		}
		return connection;
	}

	private Vertice getRandomVertice() {
		Vertice vertice = null;
		while (vertice == null || vertice.getConnections().isEmpty()) {
			vertice = getData().getVertice(random.nextInt(getData().size()));
			if (vertice.isMerged()) {
				System.out.print(vertice + ">");
				vertice = vertice.getMerge();
			}
		}
		return vertice;
	}

}
