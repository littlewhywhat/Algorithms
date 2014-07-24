package com.littlewhywhat.algorithms.knapsack;

public class SimpleItem implements KnapsackItem {

	private final int value;
	private final int weight;
	public SimpleItem(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	@Override
	public int getWeight() {
		return this.value;
	}

	@Override
	public int getValue() {
		return this.weight;
	}

	@Override
	public String toString() {
		return "[v=" + value + ", w=" + weight + "]";
	}

}
