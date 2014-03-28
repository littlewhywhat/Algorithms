package com.littlewhywhat.algorithms.baselinepredictors;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.BaselinePredictorsConfig;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.BaselinePredictorsData;

public class BaselinePredictorsAlgorithm extends AbstractAlgorithm<BaselinePredictorsConfig, BaselinePredictorsData , double[]> {
	
	public static class BaselinePredictorsConfig {
		private double lambda;
		private int maxRating;
		
		public BaselinePredictorsConfig(double lambda, int maxRating) {
			super();
			this.lambda = lambda;
			this.maxRating = maxRating;
		}
		public double getLambda() {
			return lambda;
		}
		public int getMaxRating() {
			return maxRating;
		}
		public void setLambda(double lambda) {
			this.lambda = lambda;
		}
		public void setMaxRating(int maxRating) {
			this.maxRating = maxRating;
		}
	}
	public static class BaselinePredictorsData {
		private int[][] matrix;
		private MatrixIndex[] testIndices;
		public BaselinePredictorsData(int[][] matrix, MatrixIndex[] testIndices) {
			super();
			this.matrix = matrix;
			this.testIndices = testIndices;
		}
		public int[][] getMatrix() {
			return matrix;
		}
		public MatrixIndex[] getTestIndices() {
			return testIndices;
		}
		public void setMatrix(int[][] matrix) {
			this.matrix = matrix;
		}
		public void setTestIndices(MatrixIndex[] testIndices) {
			this.testIndices = testIndices;
		}
		
		
	}
	public static class MatrixIndex {
		private int x;
		private int y;
		public MatrixIndex(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	private double divUsersItems;

	private double[] usersBias;
	
	private double[] itemsBias;
	
	private double mean;
	
	private void computeItemsBias() {
		int count;
		double itemAverageRating;
		for (int i = 0; i < itemsBias.length; i++) {
			count = 0;
			itemAverageRating = 0;
			for (int j = 0; j < usersBias.length; j++)
				if (getMatrix()[i][j] > 0) {
					count++;
					itemAverageRating += getMatrix()[i][j] - usersBias[j];
				}
			itemAverageRating /= count;
			itemsBias[i] = (itemAverageRating - mean)
					/ (count + getLambda());
		}
	}
	
	private void computeMean() {
		mean = 0;
		int count = 0;
		for (int i = 0; i < usersBias.length; i++)
			for (int j = 0; j < itemsBias.length; j++)
				if (getMatrix()[i][j] > 0) {
					mean += getMatrix()[i][j];
					count++;
				}
		mean /= count;
	}

	private void computeTestIndices() {
		double[] values = new double[getTestIndices().length];
		double value;
		for (int i = 0; i < getTestIndices().length; i++) {
			MatrixIndex entry = getTestIndices()[i];
			value = mean + usersBias[entry.getX()] + itemsBias[entry.getY()];
			if (value > getMaxRating())
				value = getMaxRating();
			values[i] = value;
		}
		setOutput(values);
	}

	private void computeUsersBias() {
		int count;
		double userAverageRating;
		for (int i = 0; i < usersBias.length; i++) {
			count = 0;
			userAverageRating = 0;
			for (int j = 0; j < itemsBias.length; j++)
				if (getMatrix()[i][j] > 0) {
					count++;
					userAverageRating += getMatrix()[i][j];
				}
			userAverageRating /= count;
			usersBias[i] = (userAverageRating - mean)
					/ (count + divUsersItems * getLambda());
		}
	}

	@Override
	public void execute() {
		init();
		computeMean();
		computeUsersBias();
		computeItemsBias();
		computeTestIndices();		
	}

	private double getLambda() {
		return getConfig().getLambda();
	}
	
	private int[][] getMatrix() {
		return getData().getMatrix();
	}

	private int getMaxRating() {
		return getConfig().getMaxRating();
	}
	private MatrixIndex[] getTestIndices() {
		return getData().getTestIndices();
	}
	
	private void init() {
		this.itemsBias = new double[getMatrix()[0].length];
		this.usersBias = new double[getMatrix().length];
		this.divUsersItems = usersBias.length / itemsBias.length;
	}
	
}
