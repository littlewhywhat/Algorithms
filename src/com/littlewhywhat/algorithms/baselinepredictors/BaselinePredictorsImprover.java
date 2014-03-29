package com.littlewhywhat.algorithms.baselinepredictors;

import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithmImprover;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.BaselinePredictorsConfig;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.MatrixIndex;
import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.PredictionDataForAlgorithm;
import com.littlewhywhat.datastructure.ArrayListSetOrAdd;
import com.littlewhywhat.datastructure.divider.SimpleArrayDivider;

public class BaselinePredictorsImprover
		extends
		AbstractAlgorithmImprover<PredictionDataForAlgorithm, BaselinePredictorsConfig, PredictionDataForAlgorithm, double[], Double> {

	private static final int NUMBER_OF_PARTS = 2;
	private double oldLambda;
	private SimpleArrayDivider<PredictionData.MatrixIndex> divider;

	@Override
	protected BaselinePredictorsConfig getPreviousConfig() {
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
		ArrayListSetOrAdd<MatrixIndex> workIndexesForAlgo = new ArrayListSetOrAdd<MatrixIndex>();
		ArrayListSetOrAdd<Integer> savedValues = new ArrayListSetOrAdd<Integer>();
		MatrixIndex[] indices = null;
		double sumRMSE = 0;
		for (int partIndex = 0; partIndex < NUMBER_OF_PARTS; partIndex++) {
			divider.goToPart(partIndex);
			saveValuesOfPartAndZeroThem(workIndexesForAlgo, savedValues);
			indices = new MatrixIndex[workIndexesForAlgo.size()];
			workIndexesForAlgo.toArray(indices);
			executeAlgorithm(indices);
			sumRMSE += computeRMSE(savedValues);
			divider.goToPart(partIndex);
			recoverMatrix(savedValues);
		}
		getData().setWorkIndices(divider.getArray());
		return sumRMSE / NUMBER_OF_PARTS;
	}

	private void recoverMatrix(List<Integer> savedValues) {
		int count = 0;
		MatrixIndex index;
		while (divider.partHasItems()) {
			index = divider.getItem();
			setMatrixValue(index.getUserIndex(), index.getItemIndex(),
					savedValues.get(count));
			count++;
		}

	}

	private void saveValuesOfPartAndZeroThem(
			ArrayListSetOrAdd<MatrixIndex> workIndexesForAlgo,
			ArrayListSetOrAdd<Integer> savedValues) {
		int count = 0;
		MatrixIndex index;
		while (divider.partHasItems()) {
			index = divider.getItem();
			savedValues.setOrGet(count,
					getMatrixValue(index.getUserIndex(), index.getItemIndex()));
			workIndexesForAlgo.setOrGet(count, index);
			setMatrixValue(index.getUserIndex(), index.getItemIndex(), 0);
			count++;
		}
		savedValues.trimToSize();
		workIndexesForAlgo.trimToSize();
	}

	private void executeAlgorithm(MatrixIndex[] workIndexesForAlgo) {
		PredictionDataForAlgorithm data = getData();
		data.setWorkIndices(workIndexesForAlgo);
		getAlgorithm().setData(data);
		getAlgorithm().execute();
	}

	private void initDivider() {
		divider = new SimpleArrayDivider<PredictionData.MatrixIndex>();
		divider.setArray(getWorkIndices());
		divider.setNumberOfParts(NUMBER_OF_PARTS);
	}

	private double computeRMSE(List<Integer> savedValues) {
		double[] output = getAlgorithm().getOutput();
		double mean = 0;
		for (int i = 0; i < output.length; i++)
			mean += Math.pow((output[i] - savedValues.get(i)), 2);
		mean /= output.length;
		return Math.sqrt(mean);
	}

	private MatrixIndex[] getWorkIndices() {
		return getData().getWorkIndices();
	}

}
