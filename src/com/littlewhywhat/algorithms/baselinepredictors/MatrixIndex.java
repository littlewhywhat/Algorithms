package com.littlewhywhat.algorithms.baselinepredictors;


public class MatrixIndex {

		private int userIndex;
		private int itemIndex;

		public MatrixIndex(int x, int y) {
			super();
			this.userIndex = x;
			this.itemIndex = y;
		}

		public int getUserIndex() {
			return userIndex;
		}

		public int getItemIndex() {
			return itemIndex;
		}

		public void setUserIndex(int userIndex) {
			this.userIndex = userIndex;
		}

		public void setItemIndex(int itemIndex) {
			this.itemIndex = itemIndex;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + userIndex;
			result = prime * result + itemIndex;
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
			if (!(obj instanceof MatrixIndex)) {
				return false;
			}
			MatrixIndex other = (MatrixIndex) obj;
			if (userIndex != other.userIndex) {
				return false;
			}
			if (itemIndex != other.itemIndex) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return "[" + userIndex + ", " + itemIndex + "]";
		}

}
