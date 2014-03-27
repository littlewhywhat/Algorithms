package com.littlewhywhat.algorithms.normalequation;

import java.text.DecimalFormat;

import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class NormalEquation extends
		AbstractAlgorithm<Integer[], DataNormalEquation, String[]> {
	private RealVector thetas;
	private RealVector testResults;
			
	@Override
	public void execute() {
		computeThetas();
		computeTestResults();
		writeAnswer();
	}

	private void writeAnswer() {
		DecimalFormat format = new DecimalFormat("0.00");
		String[] output = new String[testResults.getDimension()];
		for (int i = 0; i < testResults.getDimension(); i++)
			output[i] = format.format(testResults.getEntry(i));
		setOutput(output );
	}

	private void computeTestResults() {
		testResults = getData().getTestValues().operate(thetas);
	}

	private void computeThetas() {
		RealMatrix learnValues = getData().getLearnValues();
		RealVector learnResults = getData().getLearnResults();
		RealMatrix transRealData = getData().getLearnValues().transpose();
		RealMatrix tempData = transRealData.multiply(learnValues);
		DecompositionSolver solver = new LUDecomposition(tempData).getSolver();
		tempData = solver.getInverse();
		RealVector tempData2 = transRealData.operate(learnResults);
		thetas = tempData.operate(tempData2);
	}

}
