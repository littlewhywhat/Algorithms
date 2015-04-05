package com.littlewhywhat.algorithms.knapsack;

import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class KnapsackAlgo extends
		AbstractAlgorithm<Integer, List<KnapsackItem>, Integer> {

	private int[][] table;
	private int current = 0;
	private int cash = 1;

	@Override
	public void execute() {
		final List<KnapsackItem> items = getData();
		table = new int[2][getConfig() + 1];
		for (int i = 1; i < items.size() + 1; i++) {
			changeCurrentCash();
		
			for (int x = 0; x < getConfig() + 1; x++) {
				KnapsackItem item = items.get(i - 1);
				
				if (x - item.getWeight() < 0)
					table[current][x] = table[cash][x];
				else
					table[current][x] = table[cash][x] > (table[cash][x
							- item.getWeight()] + item.getValue()) ? table[cash][x]
							: table[cash][x - item.getWeight()]
									+ item.getValue();
			}
		}
		setOutput(table[current][getConfig()]);
	}

	private void changeCurrentCash() {
		int temp = current;
		current = cash;
		cash = temp;
	}

}
