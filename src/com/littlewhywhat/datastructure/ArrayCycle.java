package com.littlewhywhat.datastructure;

public class ArrayCycle<E> extends AbstractArrayCollection<E> {

	private int index = -1;

	public boolean cycleHasNext() {
		return this.index < this.size() - 1;
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

	private void nextIndex() {
		this.index++;
		if (this.index == this.size())
			this.index = 0;
	}

	@Override
	public void reset() {
		this.index = -1;
	}

}
