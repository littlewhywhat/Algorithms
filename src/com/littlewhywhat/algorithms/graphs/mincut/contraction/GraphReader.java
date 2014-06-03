package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class GraphReader extends TextFileInputReader<Void, Graph> {

	private ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

	private Graph createGraph() {
		Graph graph = new Graph(list.size());
		for (ArrayList<Integer> line : list) {
			int verticeIndex = line.get(0);
			for (int i = 1; i < line.size(); i++)
				graph.connect(verticeIndex, line.get(i));
		}
		return graph;
	}

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		list = new ArrayList<ArrayList<Integer>>();
		Scanner lineScanner;
		while (scanner.hasNextLine()) {
			ArrayList<Integer> line = new ArrayList<Integer>();
			list.add(line);
			lineScanner = new Scanner(scanner.nextLine());
			while (lineScanner.hasNextInt())
				line.add(lineScanner.nextInt() - 1);
			lineScanner.close();
		}
	}

	@Override
	public Graph getData() {
		setData(createGraph());
		return super.getData();
	}

}
