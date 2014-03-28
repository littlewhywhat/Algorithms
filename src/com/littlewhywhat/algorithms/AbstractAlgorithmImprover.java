package com.littlewhywhat.algorithms;


public abstract class AbstractAlgorithmImprover<ConfigType, DataType, OutputType, CheckType>
		implements
		AlgorithmImprover<ConfigType, DataType, OutputType, CheckType> {

	private DataType data;
	private PerformanceChecker<CheckType> checker;
	private AbstractAlgorithm<ConfigType, DataType, OutputType> algorithm;
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
			AbstractAlgorithm<ConfigType, DataType, OutputType> algorithm) {
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

	protected abstract ConfigType getPreviousConfig();

	protected abstract void improveConfig();

	protected abstract CheckType measurePerformance();

	@Override
	public ConfigType getConfig() {
		return algorithm.getConfig();
	}

	@Override
	public void setConfig(ConfigType config) {
		algorithm.setConfig(config);
	}

	public AbstractAlgorithm<ConfigType, DataType, OutputType> getAlgorithm() {
		return algorithm;
	}

	@Override
	public void setMaxNumberOfImprovements(int maxNumberOfImprovements) {
		this.maxNumberOfImprovements  = maxNumberOfImprovements;	
		this.countIsNotSet = false;
	}

}
