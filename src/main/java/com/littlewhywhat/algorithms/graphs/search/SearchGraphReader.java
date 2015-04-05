package com.littlewhywhat.algorithms.graphs.search;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;

public class SearchGraphReader extends
		GraphReader {

	@Override
	protected void fillGraph(Graph graph, Scanner scanner) {
		while (scanner.hasNextInt()) {
			graph.connect(scanner.nextInt() - 1, scanner.nextInt() -1 );
		}
	}

}
