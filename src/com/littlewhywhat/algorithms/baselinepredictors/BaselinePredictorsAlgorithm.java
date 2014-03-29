package com.littlewhywhat.algorithms.baselinepredictors;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.Config;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.Data;
import com.littlewhywhat.datastructure.Array;
import com.littlewhywhat.datastructure.ArrayPart;

public class BaselinePredictorsAlgorithm
		extends
		AbstractAlgorithm<Config, Data, double[]> {

	public static class Config {
		private double lambda;
		private int maxRating;

		public Config(double lambda, int maxRating) {
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

	public static class Data {
		private int[][] matrix;
		ArrayPart<MatrixIndex> testIndices;

		public int[][] getMatrix() {
			return matrix;
		}

		public void setMatrix(int[][] matrix) {
			this.matrix = matrix;
		}

		public ArrayPart<MatrixIndex> getTestIndices() {
			return testIndices;
		}

		public void setTestIndices(ArrayPart<MatrixIndex> testIndices) {
			this.testIndices = testIndices;
		}

		public Data(int[][] matrix,
				ArrayPart<MatrixIndex> testIndices) {
			super();
			this.matrix = matrix;
			this.testIndices = testIndices;
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
			if (itemAverageRating != 0) {
			itemAverageRating /= count;
			itemsBias[i] = (itemAverageRating - mean) / (count + getLambda());
			} else
				itemsBias[i] = 0;
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
		double[] values = new double[getTestIndices().size()];
		double value;
		for (int i = 0; i < values.length; i++) {
			MatrixIndex entry = getTestIndices().get(i);
			value = mean + usersBias[entry.getUserIndex()] + itemsBias[entry.getItemIndex()];
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
			if (userAverageRating != 0) {
				userAverageRating /= count;
				usersBias[i] = (userAverageRating - mean)
						/ (count + divUsersItems * getLambda());
			} else
				usersBias[i] = userAverageRating;
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

	private Array<MatrixIndex> getTestIndices() {
		return getData().getTestIndices();
	}

	private void init() {
		this.itemsBias = new double[getMatrix()[0].length];
		this.usersBias = new double[getMatrix().length];
		this.divUsersItems = usersBias.length / itemsBias.length;
	}

}
