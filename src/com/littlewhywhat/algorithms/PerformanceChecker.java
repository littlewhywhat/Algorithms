package com.littlewhywhat.algorithms;


public interface PerformanceChecker<T> {
	public enum PerformanceCheckResult {
		
		CAN_BE_IMPROVED,
		CAN_NOT_BE_IMPROVED,
		PREVIOUS_WAS_BETTER,
	}
	PerformanceCheckResult check(T value);
}
