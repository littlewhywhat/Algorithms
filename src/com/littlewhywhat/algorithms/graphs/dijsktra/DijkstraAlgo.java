package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph.DijkstraVertice;

public class DijkstraAlgo extends AbstractAlgorithm<Void, DijkstraGraph, int[]> {

	@Override
	public void execute() {
		DijkstraGraph graph = getData();
		DijkstraVertice source = graph.getSource();
		while(source.sizeConnections() != 0) {
			graph.mergeDijkstra();
		}
		int[] output = new int[10];
		int[] outputIndices = new int[] { 6, 36, 58, 81, 98, 114, 132, 164, 187, 196};
		
		for (int i = 0; i < outputIndices.length; i++) {
			output[i] = ((DijkstraVertice)graph.getVertice(outputIndices[i])).getDistanceToSource();
		}
		setOutput(output);
	}


}
