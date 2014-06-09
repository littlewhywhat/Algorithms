package com.littlewhywhat.algorithms.graphs.csc;

import com.littlewhywhat.algorithms.graphs.search.SearchGraph;

public class KosarajuGraphs {

	private final SearchGraph graph;
	private final SearchGraph reversed;

	public KosarajuGraphs(SearchGraph graph, SearchGraph reversed) {
		this.graph = graph;
		this.reversed = reversed;
	}

	public SearchGraph getGraph() {
		return this.graph;
	}

	public SearchGraph getReversed() {
		return this.reversed;
	}

}
