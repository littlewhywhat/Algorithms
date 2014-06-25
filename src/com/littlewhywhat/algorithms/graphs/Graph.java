package com.littlewhywhat.algorithms.graphs;

import java.util.Map;

public interface Graph extends Map<Integer, Vertice>, Iterable<Vertice> {
	void connect(int one, int two);
}
