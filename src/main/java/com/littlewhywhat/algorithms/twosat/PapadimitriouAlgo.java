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
		boolean[] variables = new boolean[length];
		preProcessData(variables);
		//System.out.println(getData());
		int log = (int) Math.log(length);
		for (int i = 0; i < log; i++) {
			randomize(variables);
			for (int j = 0; j < number; j++) {
				//printArray(variables);
				if (execute(variables)) {
					setOutput(true);
					return;
				}
			}
		}
		setOutput(false);
	}

	private void preProcessData(boolean[] data) {
		for (Clause clause : getData())
			clause.setVariables(data);
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
					clause.inverseSmallerVariable();
				else
					clause.inverseLargerVariable();
				return false;
			}
		return true;
	}

	private void randomize(boolean[] data) {
		for (int i = 0; i < data.length; i++)
			data[i] = random.nextBoolean();
	}

}
