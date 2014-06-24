package com.littlewhywhat.algorithms.graphs.search;

import com.littlewhywhat.algorithms.graphs.SimpleGraph;
import com.littlewhywhat.algorithms.graphs.Vertice;

public class SearchGraph extends SimpleGraph {
	public class SearchVertice extends SimpleVertice {
		private boolean isExplored = false;

		public SearchVertice(int index) {
			super(index);
		}

		public void markAsExplored() {
			this.isExplored = true;
		}

		public boolean isExplored() {
			return this.isExplored;
		}

		@Override
		public String toString() {
			return super.toString() + " [" + isExplored + "]";
		}
	}

	public void reset() {
		for (Vertice vertice : getVertices().values()) {
			((SearchVertice) vertice).isExplored = false;
		}
	}

	@Override
	protected Vertice getNewVertice(int index) {
		return new SearchVertice(index);
	}

}
