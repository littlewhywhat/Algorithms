package com.littlewhywhat.algorithms.graphs.csc;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;

public class ReversedGraphReader extends GraphReader {

	@Override
	protected void fillGraph(Graph graph, Scanner scanner) {
		// TODO Auto-generated method stub
		while (scanner.hasNext()) {
			int two = scanner.nextInt() - 1;
			int one = scanner.nextInt() - 1;
			graph.connect(one, two);
		}
	}

}
