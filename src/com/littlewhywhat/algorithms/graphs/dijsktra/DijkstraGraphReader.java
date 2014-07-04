package com.littlewhywhat.algorithms.graphs.dijsktra;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.dijsktra.DijkstraGraph.WeightedConnection;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;

public class DijkstraGraphReader extends GraphReader {

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

	@Override
	protected void fillGraph(Graph graph, Scanner scanner) {
		while (scanner.hasNextLine())
			try (Scanner line = new Scanner(scanner.nextLine())) {
				processLine(graph, line);
			}
	}

	private ConnectionInfo getConnectionInfoFromToken(String token) {
		String[] info = StringUtils.split(token, ',');
		return new ConnectionInfo(Integer.valueOf(info[CONNECTION_INFO_INDEX]),
				Integer.valueOf(info[WEIGHT_INFO_INDEX]));
	}

	private void processLine(Graph graph, Scanner line) {
		final int verticeIndex = line.nextInt();
		while (line.hasNext()) {
			ConnectionInfo info = getConnectionInfoFromToken(line.next());
			WeightedConnection connection = (WeightedConnection) graph.connect(
					verticeIndex, info.connectionIndex);
			connection.setWeight(info.weight);
		}
	}

}
