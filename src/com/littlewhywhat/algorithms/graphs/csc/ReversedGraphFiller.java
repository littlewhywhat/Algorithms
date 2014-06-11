package com.littlewhywhat.algorithms.graphs.csc;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.io.GraphFiller;

public class ReversedGraphFiller implements GraphFiller {

	@Override
	public void fill(Graph graph, Scanner scanner) {
		while (scanner.hasNext()) {
			int two = scanner.nextInt() - 1;
			int one = scanner.nextInt() - 1;
			graph.connect(one, two);
		}
	}

}
