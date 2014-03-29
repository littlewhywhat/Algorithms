package com.littlewhywhat.algorithms.baselinepredictors;

public class PredictionData {

	public static class MatrixIndex {
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
			return "[" + userIndex + ", "
					+ itemIndex + "]";
		}

	}

	public static class PredictionDataForAlgorithm {
		private int[][] matrix;
		PredictionData.MatrixIndex[] workIndices;

		public int[][] getMatrix() {
			return matrix;
		}

		public void setMatrix(int[][] matrix) {
			this.matrix = matrix;
		}

		public PredictionData.MatrixIndex[] getWorkIndices() {
			return workIndices;
		}

		public void setWorkIndices(PredictionData.MatrixIndex[] testIndices) {
			this.workIndices = testIndices;
		}

		public PredictionDataForAlgorithm(int[][] matrix,
				MatrixIndex[] testIndices) {
			super();
			this.matrix = matrix;
			this.workIndices = testIndices;
		}

	}

	PredictionData.MatrixIndex[] learnEntries;
	PredictionDataForAlgorithm algoData;

	public PredictionData(PredictionData.MatrixIndex[] learnEntries,
			PredictionData.MatrixIndex[] testEntries, int[][] matrix) {
		super();
		this.algoData = new PredictionDataForAlgorithm(matrix, testEntries);
		this.learnEntries = learnEntries;
	}

	public PredictionData.MatrixIndex[] getLearnIndices() {
		return this.learnEntries;
	}

	public PredictionData.MatrixIndex[] getTestIndices() {
		return this.algoData.getWorkIndices();
	}

	public int[][] getMatrix() {
		return this.algoData.getMatrix();
	}

	public PredictionDataForAlgorithm getAlgoData() {
		return algoData;
	}

	public void setAlgoData(PredictionDataForAlgorithm algoData) {
		this.algoData = algoData;
	}

}
