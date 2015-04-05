package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.graphs.Connection;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.ContractionGraph;

public class DijkstraGraph extends ContractionGraph {

	class DijkstraVertice extends ContractionVertice {

		protected int distanceToSource;

		DijkstraVertice(int index) {
			super(index);
		}

		int getDistanceToSource() {
			return distanceToSource;
		}

		void setDistanceToSource(int distanceToSource) {
			this.distanceToSource = distanceToSource;
		}

		@Override
		public String toString() {
			return "[" + super.toString() + ", " + distanceToSource + "]";
		}

	}

	class WeightedConnection extends SimpleConnection {

		private int weight;

		public WeightedConnection(Vertice vertice) {
			super(vertice);
		}

		void addToWeight(int value) {
			this.weight += value;
		}

		void setWeight(int value) {
			this.weight = value;
		}

		int getWeight() {
			return this.weight;
		}

		@Override
		public String toString() {
			return "Connection to " + getVertice() + " with weight "
					+ weight;
		}

	}

	private final int sourceIndex;

	public DijkstraGraph(int sourceIndex) {
		this.sourceIndex = sourceIndex;
	}

	DijkstraVertice getSource() {
		return (DijkstraVertice) this.get(sourceIndex);
	}

	@Override
	protected Vertice getNewVertice(int index) {
		return new DijkstraVertice(index);
	}

	@Override
	protected Connection getNewConnection(Vertice vertice) {
		return new WeightedConnection(vertice);
	}
	
	void mergeDijkstra() {
		DijkstraVertice source = this.getSource();
		WeightedConnection connection = getMinConnection();
		int weight = connection.getWeight();
		DijkstraVertice destination = (DijkstraVertice) connection.getVertice();
		destination.setDistanceToSource(weight);
		for (Connection vertice : getConnections(destination))
			((WeightedConnection) vertice).addToWeight(weight);
		merge(destination, source);
		getVertices().put(destination.getIndex(), destination);
	}

	private WeightedConnection getMinConnection() {
		WeightedConnection min = (WeightedConnection) getConnections(getSource()).get(0);
		for (Connection connection : getConnections(getSource())) {
			if (min.getWeight() > ((WeightedConnection) connection).getWeight())
				min = (WeightedConnection) connection;
		}
		return (WeightedConnection) min;
	}

}
