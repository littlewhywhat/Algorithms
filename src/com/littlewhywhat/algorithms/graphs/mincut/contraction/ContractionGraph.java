package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.AbstractListGraph;
import com.littlewhywhat.algorithms.graphs.Vertice;

public class ContractionGraph extends AbstractListGraph {

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

		private LinkedList<Vertice> getConnectionsList() {
			return (LinkedList<Vertice>) this.connections;
		}

	}

	public ContractionGraph(int size) {
		super(size);
	}

	void merge(ContractionVertice one, ContractionVertice two) {
		one.mergedTo = two;
		int size = two.sizeConnections();
		for (int i = 0; i < size; i++) {
			ContractionVertice vertice = (ContractionVertice) two
					.getConnectionsList().pollFirst();
			vertice = vertice.leader();
			if (!vertice.equals(two))
				two.getConnectionsList().addLast(vertice);
		}
		for (Vertice vertice : one.getConnections()) {
			vertice = ((ContractionVertice) vertice).leader();
			if (!vertice.equals(two))
				two.getConnectionsList().addLast(vertice);
		}
		one.getConnectionsList().clear();
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
