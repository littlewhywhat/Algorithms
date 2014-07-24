package com.littlewhywhat.algorithms.graphs.clustering;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;

public class ClusteringReader extends GraphReader {

	@Override
	protected void fillGraph(Graph data, Scanner scanner) {
		scanner.nextLine();
		while (scanner.hasNext()) {
			((UnionFindGraph) data).connect(scanner.nextInt(),
					scanner.nextInt(), scanner.nextInt());
		}
	}

}
