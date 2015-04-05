package com.littlewhywhat.algorithms.graphs.allpairs;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.graphs.Connection;
import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.clustering.UnionFindGraph.WeightedEdge;

public class FloydWarshalAlgo extends
		AbstractAlgorithm<Void, Graph, int[][]> {

	private class ProcessingData {
		private int CACHE_INDEX = 0;
		private int CURRENT_INDEX = 1;
		private int[][][] arrays;
		private ProcessingData(int size) {
			arrays = new int[2][size][size];
		}
		
		int[][] getCurrent() {
			return arrays[CURRENT_INDEX];
		}
		int[][] getCache() {
			return arrays[CACHE_INDEX];
		}
		
		void switchCurrentCache() {
			int temp = CURRENT_INDEX;
			CURRENT_INDEX = CACHE_INDEX;
			CACHE_INDEX = temp;
		}
	}
			
			
	@Override
	public void execute() {
		final int size = this.getData().size();
		final ProcessingData data = new ProcessingData(size);
		fillProcessingData(data);
		int[][] current;
		int[][] cache;
		
		for (int k = 0; k < size; k++) {
			current = data.getCurrent();
			cache = data.getCache();
			int caseOne;
			int caseTwo;
			for (int i = 0; i < size; i++) 
				for (int j = 0; j < size; j++) {
					caseOne = cache[i][j];
					caseTwo = cache[i][k] + cache[k][j];
					if (caseOne < caseTwo)
						current[i][j] = caseOne;
					else
						current[i][j] = caseTwo;
				}
			data.switchCurrentCache();
		}
		if (!hasNegativeCycles(data.getCache()))
			setOutput(data.getCache());
	}


	private boolean hasNegativeCycles(int[][] data) {
		for (int i = 0; i < data.length; i++)
			if (data[i][i] < 0)
				return true;
		return false;
	}


	private void fillProcessingData(ProcessingData data) {
		final Graph graph = getData();
		for (int i = 0; i < graph.size(); i++) 
			for (int j = 0; j < graph.size(); j++)
				data.getCache()[i][j] = Integer.MAX_VALUE/2;
		for (Vertice vertice: graph) 
			for (Connection connection: graph.getConnections(vertice)) {
				WeightedEdge edge = (WeightedEdge) connection;
				data.getCache()[edge.getStart().getIndex() -1 ][edge.getVertice().getIndex() -1] = edge.getWeight();
			}
		for (int i = 0; i < graph.size(); i++) {
			data.getCache()[i][i] = 0;
		}
	}

}
