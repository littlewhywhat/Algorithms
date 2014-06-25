package com.littlewhywhat.algorithms.graphs;

import java.util.Map;

public interface Graph extends Map<Integer, Vertice> {
	void connect(int one, int two);
}
