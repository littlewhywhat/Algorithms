package com.littlewhywhat.algorithms;

public interface AlgorithmImprover<ConfigType, DataType, OutputType, CheckType> {
	void setData(DataType array);

	void setConfig(ConfigType config);

	void setChecker(PerformanceChecker<CheckType> checker);

	void setAlgorithm(
			AbstractAlgorithm<ConfigType, DataType, OutputType> algorithm);

	void improve();

	void setMaxNumberOfImprovements(int maxNumberOfImprovements);
	
	ConfigType getConfig();
}
