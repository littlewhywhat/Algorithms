package com.littlewhywhat.algorithms.badparket;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.badparket.Data.Index;

public class BadParketOneTwoComputer extends
		AbstractAlgorithm<int[], LinkedList<Index>, Integer> {

	private Comparator<Index> comparator = new Comparator<Index>() {

		@Override
		public int compare(Index one, Index two) {
			int badNeighboursCountOne = one.getBadNeighboursCount();
			int badNeighboursCountTwo = two.getBadNeighboursCount();
			if (badNeighboursCountOne > badNeighboursCountTwo)
				return 1;
			if (badNeighboursCountOne < badNeighboursCountTwo)
				return -1;
			return 0;
		}

	};

	@Override
	public void execute() {

		Collections.sort(getData(), comparator);
		int sum = 0;
		if (getConfig()[0] < getConfig()[1] * 2)
			while (!getData().isEmpty()) {
				Index index = getData().pop();
				if (index.getBadNeighboursCount() != 0) {
					List<Index> list = index.getBadNeighbours();
					if (removeNeighbourFromData(list)) {
						sum += getConfig()[0];
						//System.out.println( sum);
						continue;
					}
				}
				sum += getConfig()[1];
				//System.out.println(sum);
			}
		else
			sum = getData().size() * getConfig()[1];
		setOutput(sum);

	}

	private boolean removeNeighbourFromData(List<Index> list) {
		for (Index index : list)
			if (getData().contains(index)){
				getData().remove(index);
				return true;
			}
		return false;
	}


}
