package com.littlewhywhat.algorithms.baselinepredictors;

import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.UserItemEntry;

public class BaselinePredictorsComputer {
	private double lambda;
	private double divUsersItems;
	private int maxRating;
	private int[][] matrix;
	private double[] usersBias;
	private double[] itemsBias;
	private double mean;

	public double[] compute(UserItemEntry[] testValues) {
		computeMean();
		computeUsersBias();
		computeItemsBias();
		return computeTestValues(testValues);
	}

	private void computeItemsBias() {
		int count;
		double itemAverageRating;
		for (int i = 0; i < itemsBias.length; i++) {
			count = 0;
			itemAverageRating = 0;
			for (int j = 0; j < usersBias.length; j++)
				if (matrix[i][j] > 0) {
					count++;
					itemAverageRating += matrix[i][j] - usersBias[j];
				}
			itemAverageRating /= count;
			itemsBias[i] = (itemAverageRating - mean)
					/ (count + lambda);
		}
	}

	private void computeMean() {
		mean = 0;
		int count = 0;
		for (int i = 0; i < usersBias.length; i++)
			for (int j = 0; j < itemsBias.length; j++)
				if (matrix[i][j] > 0) {
					mean += matrix[i][j];
					count++;
				}
		mean /= count;
	}

	private double[] computeTestValues(UserItemEntry[] testValues) {
		double[] values = new double[testValues.length];
		double value;
		for (int i = 0; i < testValues.length; i++) {
			UserItemEntry entry = testValues[i];
			value = mean + usersBias[entry.getUserId()] + itemsBias[entry.getItemId()];
			if (value > maxRating)
				value = maxRating;
			values[i] = value;
		}
		return values;
	}

	private void computeUsersBias() {
		int count;
		double userAverageRating;
		for (int i = 0; i < usersBias.length; i++) {
			count = 0;
			userAverageRating = 0;
			for (int j = 0; j < itemsBias.length; j++)
				if (matrix[i][j] > 0) {
					count++;
					userAverageRating += matrix[i][j];
				}
			userAverageRating /= count;
			usersBias[i] = (userAverageRating - mean)
					/ (count + divUsersItems * lambda);
		}
	}

	public void setLambda(int lambda) {
		this.lambda = lambda;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
		this.usersBias = new double[matrix.length];
		this.itemsBias = new double[matrix[0].length];
		this.divUsersItems = usersBias.length / itemsBias.length;
	}

	public void setMaxRating(int maxRating) {
		this.maxRating = maxRating;
	}

}
