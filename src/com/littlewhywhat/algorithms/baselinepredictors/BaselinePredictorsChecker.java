package com.littlewhywhat.algorithms.baselinepredictors;

import com.littlewhywhat.algorithms.PerformanceChecker;

public class BaselinePredictorsChecker implements PerformanceChecker<Double> {

	private double previousResult = 9999;
	@Override
	public PerformanceCheckResult check(
			Double value) {
		if (value < previousResult) {
			previousResult = value;
			return PerformanceCheckResult.CAN_BE_IMPROVED;
		}
		else
			return PerformanceCheckResult.PREVIOUS_WAS_BETTER;
	}

}
