package com.littlewhywhat.datastructure.collection;

public class ArrayCycleStarter<E> extends ArrayCycle<E> {
	int startIndex = 0;

	@Override
	public boolean cycleHasNext() {
		return this.getIndex() != startIndex;
		
	}

	@Override
	public void reset() {
		this.setIndex(startIndex - 1);
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
		reset();
	}
	
}
