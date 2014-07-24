package com.littlewhywhat.algorithms.knapsack;

import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class KnapsackAlgo extends
		AbstractAlgorithm<Integer, List<KnapsackItem>, Integer> {

	private int[][] table;
	
	@Override
	public void execute() {
		final List<KnapsackItem> items = getData();
		table = new int[items.size() + 1][ getConfig() + 1 ];
		for (int i = 1; i < items.size() + 1; i++ )
			for (int x = 0; x < getConfig() + 1; x++) {
				KnapsackItem item = items.get(i - 1);
				if (x -item.getWeight() < 0) 
					table[i][x] = table[i-1][x];
				else
					table[i][x] = table[i-1][x] > (table[i-1][x - item.getWeight()] + item.getValue())? table[i-1][x]: table[i-1][x -item.getWeight()] + item.getValue();
			}
		setOutput(table[items.size()][getConfig()]);
	}

}
