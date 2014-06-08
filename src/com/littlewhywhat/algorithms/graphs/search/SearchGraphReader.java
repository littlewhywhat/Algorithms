package com.littlewhywhat.algorithms.graphs.search;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.GraphReader;

public class SearchGraphReader extends GraphReader {

	@Override
	protected Graph getNewGraph(int size) {
		return new SearchGraph(size);
	}


}
