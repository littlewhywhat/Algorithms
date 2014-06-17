package com.littlewhywhat.algorithms.graphs;

import java.util.Iterator;
import java.util.List;

class SimpleConnectionsList implements ConnectionsList {

	private final List<Vertice> list;
	
	SimpleConnectionsList(List<Vertice> list) {
		this.list = list;
	}

	@Override
	public Iterator<Vertice> iterator() {
		return list.iterator();
	}

	@Override
	public Vertice get(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}
	

}
