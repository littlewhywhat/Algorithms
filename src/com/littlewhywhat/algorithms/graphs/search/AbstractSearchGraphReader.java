package com.littlewhywhat.algorithms.graphs.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.AbstractInputReader;

public abstract class AbstractSearchGraphReader extends
		AbstractInputReader<Void, SearchGraph> {
	private interface Command {
		void execute(Scanner scanner);
	}

	public interface GraphWriter extends Command {
		void setGraph(SearchGraph graph);

		SearchGraph getGraph();
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

	private String inputFilePath;
	private final VerticesCounter counter = new VerticesCounter();

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	protected abstract GraphWriter getGraphWriter();

	@Override
	public void read() {
		File file = new File(inputFilePath);
		executeCommand(file, counter);
		getGraphWriter().setGraph(new SearchGraph(counter.count()));
		executeCommand(file, getGraphWriter());
		setData(getGraphWriter().getGraph());
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
