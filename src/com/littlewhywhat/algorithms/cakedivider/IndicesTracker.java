package com.littlewhywhat.algorithms.cakedivider;

public class IndicesTracker {
	private int[] indices;
	private int divident;

	public IndicesTracker(int quantity, int divider) {
		indices = new int[divider];
		divident = quantity / divider;
		goToBeginning();
	}

	public int[] getIndices() {
		return indices;
	}

	public boolean hasNext() {
		return indices[0] < (divident - 1);
	}

	public boolean hasPrevious() {
		return indices[0] > 0;
	}

	public int[] goNext() {
		for (int i = 0; i < indices.length; i++) {
			indices[i]++;
		}
		return getIndices();
	}

	public int[] goPrevious() {
		for (int i = 0; i < indices.length; i++) {
			indices[i]--;
		}
		return getIndices();
	}

	public void goToBeginning() {
		int index = -1;
		for (int i = 0; i < indices.length; i++) {
			indices[i] = index;
			index += divident;
		}
	}
}
