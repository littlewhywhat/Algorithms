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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SimpleItem)) {
			return false;
		}
		SimpleItem other = (SimpleItem) obj;
		if (value != other.value) {
			return false;
		}
		if (weight != other.weight) {
			return false;
		}
		return true;
	}

}
