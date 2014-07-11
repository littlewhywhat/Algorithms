package com.littlewhywhat.algorithms.schedule;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class MinWeightedSum<E extends Job> extends
		AbstractAlgorithm<Void, Schedule<E>, Long> {

	@Override
	public void execute() {
		final Schedule<E> schedule = getData();
		long result = 0;
		while (!schedule.isEmpty()) {
			E job = schedule.poll();
			result += job.getWeight() * schedule.getTime();
		}
		setOutput(result);
	}

}
