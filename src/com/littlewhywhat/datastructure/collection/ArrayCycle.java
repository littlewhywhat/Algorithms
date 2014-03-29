package com.littlewhywhat.datastructure.collection;


public class ArrayCycle<E> extends AbstractArrayCollection<E> {

	private int index = -1;

	public boolean cycleHasNext() {
		return this.index + 1 < this.size();
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public E next() {
		nextIndex();
		return this.getArray()[index];
	}

	protected void nextIndex() {
		this.index++;
		if (this.index == this.size())
			this.index = 0;
	}

	@Override
	public void reset() {
		this.index = -1;
	}

	protected int getIndex() {
		return index;
	}

	protected void setIndex(int index) {
		this.index = index;
	}

}
