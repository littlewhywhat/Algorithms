package com.littlewhywhat.algorithms.graphs.dijsktra;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph.Connection;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph.DijkstraVertice;
import com.littlewhywhat.algorithms.graphs.io.GraphFiller;

public class DijkstraGraphFiller implements GraphFiller {

	private class ConnectionInfo {
		private int connectionIndex;
		private int weight;

		private ConnectionInfo(int connectionIndex, int weight) {
			this.connectionIndex = connectionIndex;
			this.weight = weight;
		}
	}

	private static final int CONNECTION_INFO_INDEX = 0;

	private static final int WEIGHT_INFO_INDEX = 1;

	private int convertVerticeIndex(int realIndex) {
		return realIndex - 1;
	}

	@Override
	public void fill(Graph graph, Scanner scanner) {
		while (scanner.hasNextLine())
			try (Scanner line = new Scanner(scanner.nextLine())) {
				processLine(graph, line);
			}
	}

	private Connection getConnectionFromGraph(Graph graph, int verticeIndex,
			int connectionIndex) {
		DijkstraVertice vertice = ((DijkstraVertice) graph
				.getVertice(verticeIndex));
		return vertice.getConnectionByIndex(connectionIndex);
	}

	private ConnectionInfo getConnectionInfoFromToken(String token) {
		String[] info = StringUtils.split(token, ',');
		return new ConnectionInfo(
				convertVerticeIndex(Integer
						.valueOf(info[CONNECTION_INFO_INDEX])),
				Integer.valueOf(info[WEIGHT_INFO_INDEX]));
	}

	private void processLine(Graph graph, Scanner line) {
		final int verticeIndex = convertVerticeIndex(line.nextInt());
		while (line.hasNext()) {
			ConnectionInfo info = getConnectionInfoFromToken(line.next());
			graph.connect(verticeIndex, info.connectionIndex);
			Connection connection = getConnectionFromGraph(graph, verticeIndex,
					info.connectionIndex);
			connection.setWeight(info.weight);
		}
	}
}
