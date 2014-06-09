package com.littlewhywhat.algorithms.graphs.search;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.search.AbstractSearchGraphReader.GraphWriter;

public class SimpleGraphWriter implements GraphWriter {

	private SearchGraph graph;

	public void setGraph(SearchGraph graph) {
		this.graph = graph;
	}

	@Override
	public void execute(Scanner scanner) {
		while (scanner.hasNextInt()) {
			graph.connect(scanner.nextInt() - 1, scanner.nextInt() -1 );
		}
	}

	public SearchGraph getGraph() {
		return this.graph;
	}
}
