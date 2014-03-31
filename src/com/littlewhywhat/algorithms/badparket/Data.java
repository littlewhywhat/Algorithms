package com.littlewhywhat.algorithms.badparket;

import java.util.LinkedList;

public class Data {
	public static class Index {
		private int x;
		private int y;

		public int getY() {
			return x;
		}

		public int getX() {
			return y;
		}

		public Index(int y, int x) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + y;
			result = prime * result + x;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Index)) {
				return false;
			}
			Index other = (Index) obj;
			if (y != other.y) {
				return false;
			}
			if (x != other.x) {
				return false;
			}
			return true;
		}

	}

	public static final byte BAD_PARKET = 0;
	public static final byte GOOD_PARKET = 1;
	public static final byte PROCESSED_PARKET = 2;
	private boolean[] parket;
	private LinkedList<Integer> indexList;
	private int yLength;

	public Data(int xLength, int yLength) {
		this.yLength = yLength;
		this.parket = new boolean[yLength * xLength];
		this.indexList = new LinkedList<Integer>();
	}

	public void add(int x, int y, boolean isBad) {
		int hash = getHash(x, y);
		this.parket[hash] = isBad;
		this.indexList.add(hash);
	}

	public int getHash(int x, int y) {
		return this.yLength * x + y;
	}

	public boolean get(int x, int y) {
		return this.parket[getHash(x, y)];
	}

	public Index pushFirst() {
		int x = this.indexList.getFirst() / this.yLength;
		int y = this.indexList.getFirst() % this.yLength;
		this.indexList.removeFirst();
		return new Index(x, y);
	}

	public void remove(int x, int y) {
		this.indexList.remove(getHash(x, y));
	}

	public boolean hasNext() {
		return indexList.size() != 0;
	}
}
