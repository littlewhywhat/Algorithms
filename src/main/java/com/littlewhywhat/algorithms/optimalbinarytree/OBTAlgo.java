package com.littlewhywhat.algorithms.optimalbinarytree;

import java.util.Comparator;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.datastructure.Heap;
import com.littlewhywhat.datastructure.SimpleHeap;

public class OBTAlgo extends AbstractAlgorithm<Void, double[], Double> {

	
	private Heap<Double> heapMin  = SimpleHeap.getMinHeap(new Comparator<Double>() {

		@Override
		public int compare(Double o1, Double o2) {
			return o1.compareTo(o2);
		}
	});
	private double[][] table;

	@Override
	public void execute() {
		final double[] data = getData();
		table = new double[data.length][data.length];
		for (int i = 0; i < data.length; i++) {
			table[i][i] = data[i];
		}
		for (int interval = 1; interval < data.length; interval++)
			for (int start = 0; start < data.length - interval; start++) {
				table[start][start + interval] = getMin(start, start + interval);
			}
		setOutput(table[0][data.length - 1]);
	}

	private double getMin(int start, int end) {
		final double[] data = getData();
		double sum = 0;
		for (int i = start; i <= end; i++ )
			sum += data[i];
		for (int root = start; root <= end; root++) {
			double firstSubtreeOpt = root != start? table[start][root - 1] : 0;
			double secondSubtreeOpt = root != end? table[root + 1][end] : 0;
			heapMin.insert(sum + firstSubtreeOpt + secondSubtreeOpt);
		}
		double answer = heapMin.poll();
		while (heapMin.size() != 0) {
			heapMin.poll();
		}
		return answer;
	}

}
