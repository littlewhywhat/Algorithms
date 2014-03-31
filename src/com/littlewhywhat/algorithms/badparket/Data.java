package com.littlewhywhat.algorithms.badparket;

public class Data {
	public class Index {
		private int x;
		private int y;
		private Data data;

		private Index(int x, int y, Data data) {
			super();
			this.x = x;
			this.y = y;
			this.data = data;
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

		public Index getDownNeighbour() {
			return this.data.get(this.x + 1, this.y);
		}

		public Index getLeftNeighbour() {
			return this.data.get(this.x, this.y - 1);
		}

		public Index getRightNeighbour() {
			return this.data.get(this.x, this.y + 1);
		}

		public Index getUpNeighbour() {
			return this.data.get(this.x - 1, this.y);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public boolean hasDownNeighbour() {
			return this.x < this.data.xLength - 1;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + y;
			result = prime * result + x;
			return result;
		}

		public boolean hasLeftNeighbour() {
			return this.y > 0;
		}

		public boolean hasRightNeighbour() {
			return this.y < this.data.yLength - 1;
		}

		public boolean hasUpNeighbour() {
			return this.x > 0;
		}

		public boolean isBad() {
			return this.data.isBad(this.x, this.y);
		}

		public boolean isProcessed() {
			return this.data.isProcessed(this.x, this.y);
		}

		public void setIsProcessed() {
			this.data.setIsProcessed(this.x, this.y);
		}

		@Override
		public String toString() {
			return "[" + x + ", " + y + "]";
		}

	}

	private boolean[] goodBadParket;
	private boolean[] processedParket;
	private int yLength;
	private int xLength;
	private int marker;

	public Data(int xLength, int yLength) {
		this.yLength = yLength;
		this.xLength = xLength;
		this.processedParket = new boolean[yLength * xLength];
		this.goodBadParket = new boolean[yLength * xLength];
	}

	public void add(int x, int y, boolean isBad) {
		if (areCorrectXY(x, y)) {
			int hash = getHash(x, y);
			this.goodBadParket[hash] = isBad;
		} else
			throw new IndexOutOfBoundsException();
	}

	private boolean areCorrectXY(int x, int y) {
		return x > -1 && x < xLength && y > -1 && y < yLength;
	}

	private Index get(int x, int y) {
		return new Index(x, y, this);
	}

	private int getHash(int x, int y) {
		return this.yLength * x + y;
	}

	public boolean hasUnProcessed() {
		this.moveMarker();
		return checkMarker();
	}

	private boolean checkMarker() {
		return this.marker < this.processedParket.length - 1;
	}

	private boolean isBad(int x, int y) {
		return this.goodBadParket[getHash(x, y)];
	}

	private boolean isProcessed(int x, int y) {
		return this.processedParket[getHash(x, y)];
	}

	public Index pop() {
		this.moveMarker();
		this.processedParket[marker] = true;
		return unHash(marker);
	}

	private void moveMarker() {
		while (this.checkMarker() && this.processedParket[this.marker])
			this.marker++;
	}

	private void setIsProcessed(int x, int y) {
		int hash = getHash(x, y);
		this.processedParket[hash] = true;
	}

	private Index unHash(int hash) {
		int x = hash / this.yLength;
		int y = hash % this.yLength;
		return new Index(x, y, this);
	}

}
