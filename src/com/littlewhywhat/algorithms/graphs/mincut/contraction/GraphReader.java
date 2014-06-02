package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.mincut.contraction.Graph.Vertice;
import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class GraphReader extends TextFileInputReader<Void, Graph> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		List<String> list = new ArrayList<String>();
		while (scanner.hasNextLine())
			list.add(scanner.nextLine());
		setData(createGraph(list));
	}

	private Graph createGraph(List<String> list) {
		Graph graph = new Graph(list.size());
		Scanner scanner;
		for (String line : list) {
			scanner = new Scanner(line);
			Vertice vertice = graph.getVertice(scanner.nextInt() - 1);
			while (scanner.hasNextInt()) 
				vertice.addToConnections(graph.getVertice(scanner.nextInt() - 1));
			scanner.close();
		}
		return graph;
	}

}
