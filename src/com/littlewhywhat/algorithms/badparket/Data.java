package com.littlewhywhat.algorithms.badparket;

import java.util.ArrayList;
import java.util.List;

public class Data {
	public class Index {
		private int x;
		private int y;
		private boolean isProcessed;
		private boolean isBad;
		private int badNeighboursCount = -1;
		
		private Data data;

		private Index(int x, int y, boolean isBad, Data data) {
			super();
			this.x = x;
			this.y = y;
			this.isBad = isBad;
			this.isProcessed = false;
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
			return getHash(x, y);
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
			return this.isBad;
		}

		public boolean isProcessed() {
			return this.isProcessed;
		}

		public void setIsProcessed() {
			this.isProcessed = true;
		}

		@Override
		public String toString() {
			return "[" + x + ", " + y + "]";
		}

		public int getBadNeighboursCount() {
			if (this.badNeighboursCount == -1) {
				this.badNeighboursCount = 0;
				if (this.hasDownNeighbour() && this.getDownNeighbour().isBad())
					badNeighboursCount++;
				if (this.hasLeftNeighbour() && this.getLeftNeighbour().isBad())
					badNeighboursCount++;
				if (this.hasRightNeighbour() && this.getRightNeighbour().isBad())
					badNeighboursCount++;
				if (this.hasUpNeighbour() && this.getUpNeighbour().isBad())
					badNeighboursCount++;
			}
			//System.out.println(this.badNeighboursCount);
			return this.badNeighboursCount;
		}

		
		
		public List<Index> getBadNeighbours() {
			List<Index> badNeighbours = new ArrayList<Index>();
			if (this.hasDownNeighbour())
				if (this.data.get(this.x + 1, this.y).isBad())
					badNeighbours.add(this.data.get(this.x + 1, this.y));
			if (this.hasUpNeighbour())
				if (this.data.get(this.x - 1, this.y).isBad())
					badNeighbours.add(this.data.get(this.x - 1, this.y));
			if (this.hasLeftNeighbour())
				if (this.data.get(this.x, this.y - 1).isBad())
					badNeighbours.add(this.data.get(this.x, this.y - 1));
			if (this.hasRightNeighbour())
				if (this.data.get(this.x, this.y + 1).isBad())
					badNeighbours.add(this.data.get(this.x, this.y + 1));
			return badNeighbours;
		}
		

	}

	private Index[] parket;
	private int yLength;
	private int xLength;
	private int marker;

	public Data(int xLength, int yLength) {
		this.yLength = yLength;
		this.xLength = xLength;
		this.parket = new Index[yLength * xLength];
	}

	public void add(int x, int y, boolean isBad) {
		if (areCorrectXY(x, y)) {
			int hash = getHash(x, y);
			this.parket[hash] = new Index(x, y, isBad, this);
		} else
			throw new IndexOutOfBoundsException();
	}

	private boolean areCorrectXY(int x, int y) {
		return x > -1 && x < xLength && y > -1 && y < yLength;
	}

	private Index get(int x, int y) {
		return this.parket[getHash(x, y)];
	}

	private int getHash(int x, int y) {
		return this.yLength * x + y;
	}

	public boolean hasUnProcessed() {
		this.moveMarker();
		return checkMarker();
	}

	private boolean checkMarker() {
		return this.marker < this.parket.length;
	}

	public Index pop() {
		this.moveMarker();
		Index index = parket[marker];
		index.setIsProcessed();
		return index;
	}

	private void moveMarker() {
		while (this.checkMarker() && this.parket[this.marker].isProcessed())
			this.marker++;
	}

}
