package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.GraphReader;

public class ContractionGraphReader extends GraphReader {

	@Override
	protected Graph getNewGraph(int size) {
		
		return new ContractionGraph(size);
	}

	public ContractionGraph getContractionGraph() {
		return (ContractionGraph) getData();
	}
}
