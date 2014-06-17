package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.graphs.io.GraphReader;
import com.littlewhywhat.algorithms.graphs.io.SimpleSizeCounter;

public class DijkstraGraphReader extends GraphReader {

	public DijkstraGraphReader(int sourceIndex) {
		this.setGraph(new DijkstraGraph(sourceIndex));
		this.setGraphFiller(new DijkstraGraphFiller());
		this.setSizeCounter(new SimpleSizeCounter());
	}
	
}
