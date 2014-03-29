package com.littlewhywhat.algorithms;


public abstract class AbstractAlgorithmImprover<DataType,AlgoConfigType, AlgoDataType, AlgoOutputType, CheckType>
		implements
		AlgorithmImprover<DataType, AlgoConfigType, AlgoDataType, AlgoOutputType, CheckType> {

	private DataType data;
	private PerformanceChecker<CheckType> checker;
	private AbstractAlgorithm<AlgoConfigType, AlgoDataType, AlgoOutputType> algorithm;
	private int maxNumberOfImprovements = 0;
	private boolean countIsNotSet = true;

	@Override
	public void setData(DataType data) {
		this.data = data;
	}

	public DataType getData() {
		return this.data;
	}

	@Override
	public void setChecker(PerformanceChecker<CheckType> checker) {
		this.checker = checker;
	}

	@Override
	public void setAlgorithm(
			AbstractAlgorithm<AlgoConfigType, AlgoDataType, AlgoOutputType> algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void improve() {
		int count = 0;
		while (countIsNotSet || count < maxNumberOfImprovements) {
			switch (checker.check(measurePerformance())) {
			case CAN_BE_IMPROVED:
				improveConfig();
				count++;
				continue;
			case PREVIOUS_WAS_BETTER:
				algorithm.setConfig(getPreviousConfig());
				return;
			default:
				return;
			}
		}
	}

	protected abstract AlgoConfigType getPreviousConfig();

	protected abstract void improveConfig();

	protected abstract CheckType measurePerformance();

	@Override
	public AlgoConfigType getConfig() {
		return algorithm.getConfig();
	}

	@Override
	public void setConfig(AlgoConfigType config) {
		algorithm.setConfig(config);
	}

	public AbstractAlgorithm<AlgoConfigType, AlgoDataType, AlgoOutputType> getAlgorithm() {
		return algorithm;
	}

	@Override
	public void setMaxNumberOfImprovements(int maxNumberOfImprovements) {
		this.maxNumberOfImprovements  = maxNumberOfImprovements;	
		this.countIsNotSet = false;
	}

}
