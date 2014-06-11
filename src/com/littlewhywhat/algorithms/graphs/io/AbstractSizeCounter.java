package com.littlewhywhat.algorithms.graphs.io;

public abstract class AbstractSizeCounter implements SizeCounter {

	private int size;

	@Override
	public int getSize() {
		return this.size;
	}

	protected void setSize(int size) {
		this.size = size;
	}
	
	protected void incrementSize() {
		this.size++;
	}
}
