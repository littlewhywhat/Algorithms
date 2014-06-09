package com.littlewhywhat.algorithms.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractListGraph implements Graph {

	private final List<Vertice> vertices = new ArrayList<Vertice>();

	public AbstractListGraph(int size) {
		for (int index = 0; index < size; index++)
			this.vertices.add(getNewVertice(index));
	}

	protected abstract Vertice getNewVertice(int index);

	@Override
	public Iterator<Vertice> iterator() {
		return this.vertices.iterator();
	}

	@Override
	public void connect(int one, int two) {
		connectVertices(this.getVertice(one), this.getVertice(two));
	}

	protected abstract void connectVertices(Vertice one, Vertice two);

	@Override
	public Vertice getVertice(int index) {
		return this.vertices.get(index);
	}

	@Override
	public int size() {
		return this.vertices.size();
	}

}
