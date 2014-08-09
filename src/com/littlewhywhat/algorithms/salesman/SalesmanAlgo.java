package com.littlewhywhat.algorithms.salesman;


import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgo extends AbstractAlgorithm<Void, List<City>, Double> {

	private class CityNode extends SimpleTreeLinkedNode {
		private final City city;
		private int[] map = new int[25];
		private CityNode(City city) {
			this.city = city;
		}

		private City getCity() {
			return this.city;
		}

		@Override
		public String toString() {
			return city.toString();
		}
	}

	private TreeLinkedNode current;

	@Override
	public void execute() {
		final List<City> cities = getData();
		current = new CityNode(cities.get(0));
		for (int index = 1; index < cities.size(); index++)
			current.getChildren().add(new CityNode(cities.get(index)));
		//printTree(current, "");
		for (int m = 2; m < cities.size(); m++) {
			recurseParent(current);
			//printTree(current, "");
		}
	}

	private void printTree(TreeLinkedNode node, String tab) {
		String str = ((CityNode)node).map != null? ((CityNode)node).map.toString(): "";
		System.out.println(tab + node + str);
		if (node.hasChildren()) {
			String extraTab = tab + ">> ";
			LinkedList<TreeLinkedNode> children = node.getChildren();
			TreeLinkedNode child = children.getFirst();
			while (child != null) {
				printTree(child, extraTab);
				child = (TreeLinkedNode) child.getNext();
			}
		}
	}

	private void addChildren(TreeLinkedNode node) {
		final TreeLinkedNode parent = node;
		node = (TreeLinkedNode) node.getNext();
		while (node != null) {
			TreeLinkedNode child = new CityNode(((CityNode) node).getCity());
			parent.getChildren().add(child);
			node = (TreeLinkedNode) node.getNext();
		}
	}

	private void recurseParent(TreeLinkedNode node) {
		((CityNode)node).map = null;
		final LinkedList<TreeLinkedNode> children =  node.getChildren();
		TreeLinkedNode child = children.getFirst();
		while (child.hasNext()) {
			if (child.hasChildren())
				recurseParent(child);
			else
				addChildren(child);
			child = (TreeLinkedNode) child.getNext();
		}
		children.removeLast();
	}
}
