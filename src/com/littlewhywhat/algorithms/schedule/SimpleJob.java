package com.littlewhywhat.algorithms.schedule;

public class SimpleJob implements Job {
	private final int weight;
	private final int length;
	
	public SimpleJob(int weight, int length) {
		this.weight = weight;
		this.length = length;
	}
	
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int getLength() {
		return length;
	}

}
