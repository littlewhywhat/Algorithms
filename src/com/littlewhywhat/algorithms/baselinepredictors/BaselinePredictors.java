package com.littlewhywhat.algorithms.baselinepredictors;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.Config;

public class BaselinePredictors extends
		AbstractAlgorithm<Integer, BaselinePredictorsData, String[]> {
	private BaselinePredictorsImprover improver;
	private BaselinePredictorsAlgorithm algorithm;

	@Override
	public void execute() {
		algorithm = new BaselinePredictorsAlgorithm();
		crossValidateLambda();
		computeTestValues();
	}

	private void crossValidateLambda() {
		improver = new BaselinePredictorsImprover();
		improver.setAlgorithm(algorithm);
		improver.setConfig(new Config(0, getConfig()));
		improver.setChecker(new BaselinePredictorsChecker());
		improver.setData(new BaselinePredictorsImprover.Data(getData()
				.getMatrix(), getData().getLearnIndices()));
		improver.improve();
	}

	private void computeTestValues() {
		algorithm.setData(getData().getAlgoData());
		algorithm.execute();
		double[] output = algorithm.getOutput();
		writeOutput(output);
	}

	private void writeOutput(double[] output) {
		String[] answers = new String[output.length];
		for (int i = 0; i < output.length; i++)
			answers[i] = String.valueOf(output[i]);
		setOutput(answers);
	}

}
