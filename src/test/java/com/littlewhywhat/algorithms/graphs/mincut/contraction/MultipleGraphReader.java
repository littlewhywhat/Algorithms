package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.ArrayList;
import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;

public class MultipleGraphReader {
	private final ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	private final GraphReader reader = new GraphReader() {

		@Override
		protected void fillGraph(Graph data, Scanner scanner) {
			while (scanner.hasNextLine()) {
				Scanner line = new Scanner(scanner.nextLine());
				ArrayList<Integer> connections = new ArrayList<Integer>();
				while (line.hasNext())
					connections.add(convertVerticeIndex(line.nextInt()));
				line.close();
				list.add(connections);
			}
		}};

	public MultipleGraphReader() {
		reader.setGraph(getEmptyGraph());
	}

	public void setInputFilePath(String inputFilePath) {
		this.reader.setInputFilePath(inputFilePath);
	}

	private Graph getEmptyGraph() {
		return new ContractionGraph();
	}

	private Graph createGraph() {
		Graph graph = getEmptyGraph();
		for (ArrayList<Integer> line : list) {
			int verticeIndex = line.get(0);
			for (int i = 1; i < line.size(); i++)
				graph.connect(verticeIndex, line.get(i));
		}
		return graph;
	}

	public Graph getGraphClone() {
		if (this.list.isEmpty())
			reader.read();
		return createGraph();
	}
}
