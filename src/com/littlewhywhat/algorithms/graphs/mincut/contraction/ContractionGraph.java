package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.Iterator;
import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.SimpleGraph;
import com.littlewhywhat.algorithms.graphs.Vertice;

public class ContractionGraph extends SimpleGraph {

	public class ContractionVertice extends SimpleVertice {

		private LinkedList<ContractionVertice> feedback = new LinkedList<ContractionVertice>();
		
		public ContractionVertice(int index) {
			super(index);
		}

		private void changeConnection(ContractionVertice old,
				ContractionVertice fresh) {
			while(feedback.remove(old))
				feedback.add(fresh);
			while(getLinkedConnections(this).remove(old))
				getLinkedConnections(this).add(fresh);
		}

		private void removeSelfLoops() {
			Iterator<Vertice> iterator = getLinkedConnections(this).listIterator();
			while (iterator.hasNext())
				if (iterator.next().equals(this))
					iterator.remove();
			Iterator<ContractionVertice> fbIterator = feedback.listIterator();
			while (fbIterator.hasNext())
				if (fbIterator.next().equals(this))
					fbIterator.remove();
		}

	}

	public void merge(ContractionVertice one, ContractionVertice two) {
		for (ContractionVertice vertice: one.feedback)
			vertice.changeConnection(one, two);
		getLinkedConnections(two).addAll(getLinkedConnections(one));
		two.feedback.addAll(one.feedback);
		two.removeSelfLoops();
		getVertices().remove(one.getIndex());
		getLinkedConnections(one).clear();
	}

	@Override
	protected Vertice getNewVertice(int index) {
		return new ContractionVertice(index);
	}

	@Override
	public void connect(int one, int two) {
		super.connect(one, two);
		ContractionVertice verticeOne = (ContractionVertice) get(one);
		ContractionVertice verticeTwo = (ContractionVertice) get(two);
		verticeTwo.feedback.add(verticeOne);
	}

	
	
}
