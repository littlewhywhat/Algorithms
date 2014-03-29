package com.littlewhywhat.algorithms;

public interface AlgorithmImprover<DataType, AlgoConfigType, AlgoDataType, AlgoOutputType, CheckType> {
	void setData(DataType array);

	void setConfig(AlgoConfigType config);

	void setChecker(PerformanceChecker<CheckType> checker);

	void setAlgorithm(
			AbstractAlgorithm<AlgoConfigType, AlgoDataType, AlgoOutputType> algorithm);

	void improve();

	void setMaxNumberOfImprovements(int maxNumberOfImprovements);

	AlgoConfigType getConfig();
}
