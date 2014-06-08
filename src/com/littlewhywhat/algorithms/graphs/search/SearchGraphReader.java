package com.littlewhywhat.algorithms.graphs.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.AbstractInputReader;

public class SearchGraphReader extends AbstractInputReader<Void, SearchGraph> {

	private interface Command {
		void execute(Scanner scanner);
	}

	private class VerticesCounter implements Command {

		private int count;

		@Override
		public void execute(Scanner scanner) {
			while (scanner.hasNextInt()) {
				int verticeNumber = scanner.nextInt();
				if (verticeNumber > count)
					count = verticeNumber;
			}
		}

		public int count() {
			return this.count;
		}
	}

	private class GraphWriter implements Command {

		private SearchGraph graph;

		public void setGraph(SearchGraph graph) {
			this.graph = graph;
		}

		@Override
		public void execute(Scanner scanner) {
			while (scanner.hasNextInt()) {
				graph.connect(scanner.nextInt() - 1, scanner.nextInt() -1 );
			}
		}

		public SearchGraph getGraph() {
			return this.graph;
		}
	}

	private String inputFilePath;
	private final VerticesCounter counter = new VerticesCounter();
	private final GraphWriter writer = new GraphWriter();

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	@Override
	public void read() {
		File file = new File(inputFilePath);
		executeCommand(file, counter);
		writer.setGraph(new SearchGraph(counter.count()));
		executeCommand(file, writer);
		setData(writer.getGraph());
	}

	private void executeCommand(File file, Command command) {
		try (Scanner scanner = new Scanner(file)) {
			command.execute(scanner);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputFilePath);
		} catch (InputMismatchException e) {
			System.out.println("Unable to read file: " + inputFilePath);
		}
	}

}
