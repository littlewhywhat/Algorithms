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
		int size = two.sizeConnections();
		for (int i = 0; i < size; i++) {
			ContractionVertice vertice = (ContractionVertice) this
					.getConnections(two).pollFirst();
			vertice = vertice.leader();
			if (!vertice.equals(two))
				this.getConnections(two).addLast(vertice);
		}
		for (Vertice vertice : one.getConnections()) {
			vertice = ((ContractionVertice) vertice).leader();
			if (!vertice.equals(two))
				this.getConnections(two).addLast(vertice);
		}
		this.getConnections(one).clear();
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
