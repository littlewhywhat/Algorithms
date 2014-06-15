package com.littlewhywhat.algorithms.graphs.dijsktra;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph.Connection;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph.DijkstraVertice;
import com.littlewhywhat.algorithms.graphs.io.GraphFiller;

public class DijkstraGraphFiller implements GraphFiller {

	@Override
	public void fill(Graph graph, Scanner scanner) {
		Scanner line;
		while (scanner.hasNextLine()) {
			line = new Scanner(scanner.nextLine());
			int verticeIndex = line.nextInt() - 1;
			while (line.hasNext()) {
				String lineConnectionWithWeight = line.next();
				String[] connectionWithWeight = StringUtils.split(
						lineConnectionWithWeight, ',');
				int connectionIndex = Integer.valueOf(connectionWithWeight[0]) - 1;
				int weight = Integer.valueOf(connectionWithWeight[1]);
				graph.connect(verticeIndex, connectionIndex);
				DijkstraVertice vertice = ((DijkstraVertice) graph.getVertice(verticeIndex));
				Connection connection = vertice.getConnectionByIndex(connectionIndex);
				connection.setWeight(weight);
			}
		}

	}

}
