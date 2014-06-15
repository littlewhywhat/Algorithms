package com.littlewhywhat.algorithms.graphs.dijsktra;

import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.algorithms.graphs.mincut.contraction.ContractionGraph;

public class DijkstraGraph extends ContractionGraph {
	class DijkstraVertice extends ContractionVertice {

		private int distanceToSource;

		public DijkstraVertice(int index) {
			super(index);
		}

		public int getDistanceToSource() {
			return distanceToSource;
		}

		public void setDistanceToSource(int distanceToSource) {
			this.distanceToSource = distanceToSource;
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
			return "[" + super.toString() + ", " + distanceToSource + "]";
		}

	}

	class Connection implements Vertice {

		private final Vertice vertice;
		private int weight;

		public Connection(Vertice vertice) {
			this.vertice = vertice;
		}

		int getWeight() {
			return this.weight;
		}

		void setWeight(int value) {
			this.weight = value;
		}

		@Override
		public Vertice getConnection(int index) {
			return this.vertice.getConnection(index);
		}

		@Override
		public Iterable<Vertice> getConnections() {
			return this.vertice.getConnections();
		}

		@Override
		public int sizeConnections() {
			return this.vertice.sizeConnections();
		}

		@Override
		public int getIndex() {
			return this.vertice.getIndex();
		}

		@Override
		public int hashCode() {
			return ((DijkstraVertice) this.vertice).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Connection)) {
				return false;
			}
			DijkstraVertice other = (DijkstraVertice) ((Connection) obj).vertice;
			return ((DijkstraVertice) this.vertice).equals(other);
		}

		@Override
		public String toString() {
			return "[" + (DijkstraVertice)vertice + ", " + weight + "]";
		}

		public void addWeight(int value) {
			this.weight += value;
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
				new Connection(getVertice(two)));
	}

	@Override
	protected Vertice getNewVertice(int index) {
		return new DijkstraVertice(index);
	}

	
	public void mergeDijkstra(DijkstraVertice source, Connection connection) {
		DijkstraVertice vertice = (DijkstraVertice)connection.vertice;
		vertice.setDistanceToSource(connection.weight);
		for (Vertice connectionOfConnection : vertice.getConnections())
			((Connection)connectionOfConnection).addWeight(connection.weight);
		this.merge(vertice, source);
	}
	

}
