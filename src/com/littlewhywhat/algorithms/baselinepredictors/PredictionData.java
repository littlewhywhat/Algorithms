package com.littlewhywhat.algorithms.baselinepredictors;

public class PredictionData {
	public static class UserItemEntry {
		private int userId;
		private int itemId;		
		private int value;
		public UserItemEntry(int userId, int itemId, int value) {
			super();
			this.userId = userId;
			this.itemId = itemId;
			this.value = value;
		}
		public int getItemId() {
			return itemId;
		}
		public int getUserId() {
			return userId;
		}
		public int getValue() {
			return value;
		}
		public void setItemId(int itemId) {
			this.itemId = itemId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public void setValue(int value) {
			this.value = value;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + itemId;
			result = prime * result + userId;
			result = prime * result + value;
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
			if (!(obj instanceof UserItemEntry)) {
				return false;
			}
			UserItemEntry other = (UserItemEntry) obj;
			if (itemId != other.itemId) {
				return false;
			}
			if (userId != other.userId) {
				return false;
			}
			if (value != other.value) {
				return false;
			}
			return true;
		}
		
		
	}

	int[][] matrix;	
	UserItemEntry[] learnEntries;
	UserItemEntry[] testEntries;
	public PredictionData(UserItemEntry[] learnEntries,
			UserItemEntry[] testEntries, int[][] matrix) {
		super();
		this.matrix = matrix;
		this.learnEntries = learnEntries;
		this.testEntries = testEntries;
	}
	public UserItemEntry[] getLearnEntries() {
		return learnEntries;
	}
	public UserItemEntry[] getTestEntries() {
		return testEntries;
	}
	public int[][] getMatrix() {
		return matrix;
	}
	
}
