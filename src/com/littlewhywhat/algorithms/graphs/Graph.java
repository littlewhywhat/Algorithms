package com.littlewhywhat.algorithms.graphs;

import java.util.Map;

public interface Graph extends Map<Integer, Vertice>, Iterable<Vertice> {
	Connection connect(int one, int two);
	UnmodifiableList<Connection> getConnections(Vertice vertice);
}
