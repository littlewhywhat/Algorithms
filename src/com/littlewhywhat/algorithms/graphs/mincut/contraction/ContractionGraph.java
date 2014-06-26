package com.littlewhywhat.algorithms.graphs.mincut.contraction;

import java.util.Iterator;
import java.util.LinkedList;

import com.littlewhywhat.algorithms.graphs.Connection;
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
			int count = 0;
			Iterator<Connection> iterator = getLinkedConnections(this)
					.listIterator();
			while (iterator.hasNext())
				if (iterator.next().getVertice().equals(old)) {
					iterator.remove();
					count++;
				}
			for (int i = 0; i < count; i++)
				getLinkedConnections(this).add(getNewConnection(fresh));
		}

		private void changeFeedback(ContractionVertice old,
				ContractionVertice fresh) {
			while (feedback.remove(old))
				feedback.add(fresh);
		}

		private void removeSelfLoops() {
			Iterator<Connection> iterator = getLinkedConnections(this)
					.listIterator();
			while (iterator.hasNext())
				if (iterator.next().getVertice().equals(this))
					iterator.remove();
			Iterator<ContractionVertice> fbIterator = feedback.listIterator();
			while (fbIterator.hasNext())
				if (fbIterator.next().equals(this))
					fbIterator.remove();
		}

	}

	public void merge(ContractionVertice one, ContractionVertice two) {
		//System.out.println("premerge");
		//System.out.println(one + " " + getLinkedConnections(one));
		//System.out.println(two + " " + getLinkedConnections(two));
		//System.out.println("feedback: " + one + one.feedback);
		for (ContractionVertice vertice : one.feedback)
			vertice.changeConnection(one, two);
		//System.out.println("afterchangingfeedback");
		//System.out.println(one + " " + getLinkedConnections(one));
		//System.out.println(two + " " + getLinkedConnections(two));
		for (Connection connection : getLinkedConnections(one))
			((ContractionVertice) connection.getVertice()).changeFeedback(one,
					two);
		//System.out.println("afterchangingconnections");
		//System.out.println(one + " " + getLinkedConnections(one));
		//System.out.println(two + " " + getLinkedConnections(two));
		getLinkedConnections(two).addAll(getLinkedConnections(one));
		two.feedback.addAll(one.feedback);
		two.removeSelfLoops();
		getVertices().remove(one.getIndex());
		getLinkedConnections(one).clear();
		//System.out.println("aftermerge");
		//System.out.println(one + " " + getLinkedConnections(one));
		//System.out.println(two + " " + getLinkedConnections(two));
	}

	@Override
	protected Vertice getNewVertice(int index) {
		return new ContractionVertice(index);
	}

	@Override
	public Connection connect(int one, int two) {
		Connection connection = super.connect(one, two);
		ContractionVertice verticeOne = (ContractionVertice) get(one);
		ContractionVertice verticeTwo = (ContractionVertice) get(two);
		verticeTwo.feedback.add(verticeOne);
		return connection;
	}

}
