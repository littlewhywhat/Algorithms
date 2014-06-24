package com.littlewhywhat.algorithms.graphs.io;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public abstract class GraphReader extends TextFileInputReader<Void, Graph> {

	public void setGraph(Graph graph) {
		this.setData(graph);
	}

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		fillGraph(getData(), scanner);
	}

	protected abstract void fillGraph(Graph data, Scanner scanner);
}
