package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.ContractionGraph;

public class DijkstraGraph extends ContractionGraph {
	class DijkstraVertice extends ContractionVertice {

		protected int intValue;

		public DijkstraVertice(int index) {
			super(index);
		}

		public int getDistanceToSource() {
			return intValue;
		}

		public void setDistanceToSource(int distanceToSource) {
			this.intValue = distanceToSource;
		}

		public Connection getConnectionByIndex(int index) {
			for (Vertice vertice : this.getConnections()) {
				Connection connection = (Connection) vertice;
				if (connection.getIndex() == index)
					return connection;
			}
			return null;
		}

		@Override
		public String toString() {
			return "[" + super.toString() + ", " + intValue + "]";
		}

	}

	class Connection extends DijkstraVertice {

		public Connection(int index) {
			super(index);
		}

		public void addToWeight(int value) {
			this.intValue += value;
		}
		
		public void setWeight(int value) {
			this.intValue = value;
		}
		
		public int getWeight() {
			return this.intValue;
		}
		
		@Override
		public int getDistanceToSource() {
			return getConnection().getDistanceToSource();
		}

		@Override
		public void setDistanceToSource(int distanceToSource) {
			getConnection().setDistanceToSource(distanceToSource);
		}
		
		private DijkstraVertice getConnection() {
			return (DijkstraVertice) getVertice(this.getIndex());
		}

		@Override
		public String toString() {
			return "Connection to " + getConnection() + " with weight " + intValue;
		}
		
		
	}

	private final int sourceIndex;

	public DijkstraGraph(int sourceIndex) {
		this.sourceIndex = sourceIndex;
	}

	public DijkstraVertice getSource() {
		return (DijkstraVertice) this.getVertice(sourceIndex);
	}

	@Override
	public void connect(int one, int two) {
		this.getConnections(getVertice(one)).add(
				new Connection(two));
	}

	@Override
	protected Vertice getNewVertice(int index) {
		return new DijkstraVertice(index);
	}
}
