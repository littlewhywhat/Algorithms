package com.littlewhywhat.algorithms.graphs;

import java.util.List;

public interface Graph extends List<Vertice> {
	void connect(int one, int two);
}
