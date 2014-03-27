package com.littlewhywhat.algorithms.baselinepredictors;

public class PredictionConfig {
	private int maxRateValue;
	private int numOfItems;
	private int numOfUsers;
	public PredictionConfig(int maxRateValue, int numOfItems, int numOfUsers) {
		super();
		this.maxRateValue = maxRateValue;
		this.numOfItems = numOfItems;
		this.numOfUsers = numOfUsers;
	}
	public int getMaxRateValue() {
		return maxRateValue;
	}
	public int getNumOfItems() {
		return numOfItems;
	}
	public int getNumOfUsers() {
		return numOfUsers;
	}
}
