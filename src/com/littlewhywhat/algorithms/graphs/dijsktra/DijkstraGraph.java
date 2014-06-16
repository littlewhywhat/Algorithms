package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.ContractionGraph;

public class DijkstraGraph extends ContractionGraph {
	public class DijkstraVertice extends ContractionVertice {

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

		@Override
		protected boolean leaderEquals(ContractionVertice contractionVertice) {
			return super.leaderEquals(contractionVertice);
		}

		@Override
		protected void setMergedTo(ContractionVertice two) {
			super.setMergedTo(two);
		}

		@Override
		public Vertice getConnection(int index) {
			return DijkstraGraph.this.getConnections(this).get(index);
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
			return (DijkstraVertice) DijkstraGraph.this.getVertice(this.getIndex());
		}
 
		@Override
		public Iterable<Vertice> getConnections() {
			return getConnection().getConnections();
		}
		
		@Override
		public String toString() {
			return "Connection to " + getConnection() + " with weight " + intValue;
		}

		@Override
		protected boolean leaderEquals(ContractionVertice contractionVertice) {
			return this.getConnection().leaderEquals(contractionVertice);
		}

		@Override
		protected void setMergedTo(ContractionVertice two) {
			this.getConnection().setMergedTo(two);
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
	
	public void mergeDijkstra() {
		DijkstraVertice source = this.getSource();
		Connection connection = getMinConnection();
		int weight = connection.intValue;
		connection.setDistanceToSource(weight);
		for (Vertice vertice : connection.getConnections())
			((Connection) vertice).addToWeight(weight);
		merge(connection, source);
	}

	private Connection getMinConnection() {
		Connection min = (Connection) this.getSource().getConnection(0);
		for (Vertice vertice : getSource().getConnections()) {
			Connection connection = (Connection) vertice;
			if (min.getWeight() > connection.getWeight())
				min = connection;
		}
		return min;
	}

	@Override
	public Vertice getVertice(int index) {
		return super.getVerticeSimple(index);
	}
}
