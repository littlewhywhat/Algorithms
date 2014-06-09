package com.littlewhywhat.algorithms.graphs.csc;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.search.AbstractSearchGraphReader;
import com.littlewhywhat.algorithms.graphs.search.SearchGraphReader;
import com.littlewhywhat.algorithms.graphs.search.SimpleGraphWriter;
import com.littlewhywhat.algorithms.io.AbstractInputReader;

public class KosarajuGraphsReader extends
		AbstractInputReader<Void, KosarajuGraphs> {

	private class ReversedGraphWriter extends SimpleGraphWriter {

		@Override
		public void execute(Scanner scanner) {
			while (scanner.hasNextInt()) {
				int two = scanner.nextInt() - 1;
				int one = scanner.nextInt() - 1;
				getGraph().connect(one, two);
			}
		}

	}

	private class ReversedSearchGraphReader extends AbstractSearchGraphReader {

		private ReversedGraphWriter writer = new ReversedGraphWriter();

		@Override
		protected GraphWriter getGraphWriter() {
			return writer;
		}

	}

	private SearchGraphReader reader = new SearchGraphReader();
	private ReversedSearchGraphReader reversedReader = new ReversedSearchGraphReader();

	public void setInputFilePath(String inputFilePath) {
		reader.setInputFilePath(inputFilePath);
		reversedReader.setInputFilePath(inputFilePath);
	}

	@Override
	public void read() {
		reader.read();
		reversedReader.read();
		setData(new KosarajuGraphs(reader.getData(), reversedReader.getData()));

	}

}
