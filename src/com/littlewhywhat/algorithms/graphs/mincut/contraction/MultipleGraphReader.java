package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.ArrayList;
import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.io.GraphFiller;
import com.littlewhywhat.algorithms.graphs.io.GraphReader;
import com.littlewhywhat.algorithms.graphs.io.SimpleSizeCounter;
import com.littlewhywhat.algorithms.graphs.io.SizeCounter;

public class MultipleGraphReader {
	private final ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	private final GraphReader reader = new GraphReader();
	private final SizeCounter sizeCounter = new SimpleSizeCounter();
	private final GraphFiller filler = new GraphFiller() {

		@Override
		public void fill(Graph graph, Scanner scanner) {
			while (scanner.hasNextLine()) {
				Scanner line = new Scanner(scanner.nextLine());
				ArrayList<Integer> connections = new ArrayList<Integer>();
				while (line.hasNext())
					connections.add(line.nextInt() - 1);
				line.close();
				list.add(connections);
			}

		}
	};

	public MultipleGraphReader() {
		reader.setGraphFiller(filler);
		reader.setSizeCounter(sizeCounter);
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
		graph.setSize(list.size());
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
