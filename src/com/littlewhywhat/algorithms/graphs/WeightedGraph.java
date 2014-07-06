package com.littlewhywhat.algorithms.graphs;


public class WeightedGraph<I extends Id> extends SimpleDirectedGraph<I> {
	private class WeightedEdge extends Edge {
		private int weight;
		private WeightedEdge(Vertice one, Vertice two) {
			super(one, two);
		}
		
		private void setWeight(int value) {
			this.weight = value;
		}
	}

	public void connect(I one, I two, int weight) {
		WeightedEdge edge = (WeightedEdge) connectAndGet(one, two);
		
	}
}
