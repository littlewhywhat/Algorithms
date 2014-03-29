package com.littlewhywhat.algorithms.baselinepredictors;

import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithmImprover;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.Config;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsImprover.Data;
import com.littlewhywhat.datastructure.ArrayListSetOrAdd;
import com.littlewhywhat.datastructure.ArrayPart;
import com.littlewhywhat.datastructure.divider.ArrayDivider;

public class BaselinePredictorsImprover
		extends
		AbstractAlgorithmImprover<Data, Config, BaselinePredictorsAlgorithm.Data, double[], Double> {

	public static class Data {
		private int[][] matrix;
		private MatrixIndex[] indices;

		public int[][] getMatrix() {
			return matrix;
		}

		public MatrixIndex[] getIndices() {
			return indices;
		}

		public Data(int[][] matrix, MatrixIndex[] indices) {
			super();
			this.matrix = matrix;
			this.indices = indices;
		}
	}

	private static final int NUMBER_OF_PARTS = 2;
	private double oldLambda;
	private ArrayDivider<MatrixIndex> divider;

	@Override
	protected Config getPreviousConfig() {
		getConfig().setLambda(oldLambda);
		return getConfig();
	}

	@Override
	protected void improveConfig() {
		oldLambda = getConfig().getLambda();
		getConfig().setLambda(oldLambda + 1);
	}

	private int[][] getMatrix() {
		return getData().getMatrix();
	}

	private void setMatrixValue(int userIndex, int itemIndex, int value) {
		getMatrix()[userIndex][itemIndex] = value;
	}

	private int getMatrixValue(int userIndex, int itemIndex) {
		return getMatrix()[userIndex][itemIndex];
	}

	@Override
	protected Double measurePerformance() {
		initDivider();
		ArrayListSetOrAdd<Integer> savedValues = new ArrayListSetOrAdd<Integer>();
		ArrayPart<MatrixIndex> part;
		double sumRMSE = 0;
		for (int partIndex = 0; partIndex < NUMBER_OF_PARTS; partIndex++) {
			part = divider.getPart(partIndex);
			saveValuesOfPartAndZeroThem(part, savedValues);
			executeAlgorithm(part);
			sumRMSE += computeRMSE(savedValues);
			recoverMatrix(part, savedValues);
		}
		return sumRMSE / NUMBER_OF_PARTS;
	}

	private void recoverMatrix(ArrayPart<MatrixIndex> part,
			List<Integer> savedValues) {
		int count = 0;
		for (MatrixIndex index : part) {
			setMatrixValue(index.getUserIndex(), index.getItemIndex(),
					savedValues.get(count));
			count++;
		}
	}

	private void saveValuesOfPartAndZeroThem(ArrayPart<MatrixIndex> part,
			ArrayListSetOrAdd<Integer> savedValues) {
		int count = 0;
		for (MatrixIndex index : part) {
			savedValues.setOrGet(count,
					getMatrixValue(index.getUserIndex(), index.getItemIndex()));
			setMatrixValue(index.getUserIndex(), index.getItemIndex(), 0);
			count++;
		}
		savedValues.trimToSize();
	}

	private void executeAlgorithm(ArrayPart<MatrixIndex> part) {
		getAlgorithm().setData(
				new BaselinePredictorsAlgorithm.Data(getData().getMatrix(),
						part));
		getAlgorithm().execute();
	}

	private void initDivider() {
		divider = ArrayDivider.getInstance(getData().getIndices(),
				NUMBER_OF_PARTS);
	}

	private double computeRMSE(List<Integer> savedValues) {
		double[] output = getAlgorithm().getOutput();
		double mean = 0;
		for (int i = 0; i < output.length; i++)
			mean += Math.pow((output[i] - savedValues.get(i)), 2);
		mean /= output.length;
		return Math.sqrt(mean);
	}

}
