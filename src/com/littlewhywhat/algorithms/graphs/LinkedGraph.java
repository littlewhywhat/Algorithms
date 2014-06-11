package com.littlewhywhat.algorithms.graphs;

import java.util.LinkedList;
import java.util.List;

public class LinkedGraph extends AbstractListGraph {

	public LinkedGraph(int size) {
		super(size);
	}

	@Override
	protected List<Vertice> getNewConnectionsList() {
		return new LinkedList<Vertice>();
	}

	protected LinkedList<Vertice> getConnections(Vertice vertice) {
		return (LinkedList<Vertice>) super.getConnections(vertice);
	}
}
