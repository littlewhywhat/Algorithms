package com.littlewhywhat.algorithms.graphs.search;

public class SearchGraphReader extends AbstractSearchGraphReader {

	private SimpleGraphWriter writer = new SimpleGraphWriter();

	@Override
	protected GraphWriter getGraphWriter() {
		return writer;
	}

}
