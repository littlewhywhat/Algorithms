package com.littlewhywhat.algorithms.graphs.search;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.io.GraphFiller;

public class SearchGraphFiller implements GraphFiller {

	@Override
	public void fill(Graph graph, Scanner scanner) {
		while (scanner.hasNextInt()) {
			graph.connect(scanner.nextInt() - 1, scanner.nextInt() -1 );
		}
	}

}
