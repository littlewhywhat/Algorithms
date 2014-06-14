package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import com.littlewhywhat.algorithms.graphs.LinkedGraph;
import com.littlewhywhat.algorithms.graphs.Vertice;

public class ContractionGraph extends LinkedGraph {

	class ContractionVertice extends SimpleVertice {

		public ContractionVertice(int index) {
			super(index);
		}

		private ContractionVertice mergedTo;

		private ContractionVertice leader() {
			ContractionVertice leader = this;
			while (leader.mergedTo != null)
				leader = leader.mergedTo;
			return leader;
		}

		@Override
		public String toString() {
			return "[" + super.toString() + ">" + leader().getIndex() + "]";
		}

		@Override
		public Vertice getConnection(int index) {
			return ((ContractionVertice) super.getConnection(index)).leader();
		}

		private void addConnectionsOf(ContractionVertice one) {
			for (Vertice vertice : one.getConnections()) {
				vertice = ((ContractionVertice) vertice).leader();
				if (!vertice.equals(this))
					ContractionGraph.this.getConnections(this).addLast(vertice);
			}
		};

	}

	public ContractionGraph() {
		super();
	}

	public ContractionGraph(int size) {
		super(size);
	}

	void merge(ContractionVertice one, ContractionVertice two) {
		one.mergedTo = two;
		clearConnections(two);
		two.addConnectionsOf(one);
		this.getConnections(one).clear();
	}

	private void clearConnections(ContractionVertice vertice) {
		int size = vertice.sizeConnections();
		for (int i = 0; i < size; i++) {
			ContractionVertice connection = (ContractionVertice) this
					.getConnections(vertice).pollFirst();
			connection = connection.leader();
			if (!connection.equals(vertice))
				this.getConnections(vertice).addLast(connection);
		}
	}

	@Override
	protected Vertice getNewVertice(int index) {
		return new ContractionVertice(index);
	}

	@Override
	public Vertice getVertice(int index) {
		return ((ContractionVertice) super.getVertice(index)).leader();
	}

}
