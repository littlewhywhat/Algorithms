package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph.DijkstraVertice;

public class DijkstraAlgo extends AbstractAlgorithm<Void, DijkstraGraph, int[]> {

	@Override
	public void execute() {
		DijkstraGraph graph = getData();
		DijkstraVertice source = graph.getSource();
		while(graph.getConnections(source).size() != 0) {
			graph.mergeDijkstra();
		}
		setOutput();
	}

	private void setOutput() {
		DijkstraGraph graph = getData();
		int[] output = new int[graph.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = ((DijkstraVertice)graph.get(i)).getDistanceToSource();
		}
		setOutput(output);
	}


}
