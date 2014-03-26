package com.littlewhywhat.algorithms.normalequation;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class DataNormalEquation {
	RealMatrix learnValues;
	RealVector learnResults;
	RealMatrix testValues;
	public RealMatrix getLearnValues() {
		return learnValues;
	}
	public void setLearnValues(RealMatrix learnValues) {
		this.learnValues = learnValues;
	}
	public RealVector getLearnResults() {
		return learnResults;
	}
	public void setLearnResults(RealVector learnResults) {
		this.learnResults = learnResults;
	}
	public RealMatrix getTestValues() {
		return testValues;
	}
	public void setTestValues(RealMatrix testValues) {
		this.testValues = testValues;
	}
}
