package com.littlewhywhat.algorithms.twosat;

import java.util.List;
import java.util.Random;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class PapadimitriouAlgo extends
		AbstractAlgorithm<Integer, List<SimpleClause>, Boolean> {

	private final Random random = new Random();

	@Override
	public void execute() {
		int length = getConfig();
		int number = 2 * (length ^ 2);
		boolean[] data = new boolean[length];
		for (Clause clause : getData())
			clause.setVariables(data);
		//System.out.println(getData());
		int log = (int) Math.log(length);
		for (int i = 0; i < log; i++) {
			randomize(data);
			for (int j = 0; j < number; j++) {
				//printArray(data);
				if (execute(data)) {
					setOutput(true);
					return;
				}
			}
		}
		setOutput(false);
	}

	private void printArray(boolean[] data) {
		System.out.print("[");
		for (boolean variable : data)
			System.out.print(variable + ",");
		System.out.println("]");
	}

	private boolean execute(boolean[] data) {
		for (SimpleClause clause : getData())
			if (!clause.check()) {
				if (random.nextBoolean())
					clause.inverseFirstVariable();
				else
					clause.inverseSecondVariable();
				return false;
			}
		return true;
	}

	private void randomize(boolean[] data) {
		for (int i = 0; i < data.length; i++)
			data[i] = random.nextBoolean();
	}

}
