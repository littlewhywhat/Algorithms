package com.littlewhywhat.algorithms.salesman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgo extends AbstractAlgorithm<Void, List<City>, Double> {

	private class CityNode extends SimpleNode {
		private final City city;

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

	private List<CityNode> cityNodes = new ArrayList<CityNode>();
	private Node cache;
	private Node current;

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

	private void printTree(Node node, String tab) {
		System.out.println(tab + node);
		if (node.hasChildren()) {
			String extraTab = tab + ">> ";
			for (Node cityNode : node.getChildren()) 
				printTree(cityNode, extraTab);
		}
	}

	private void addChildren(Node node, ListIterator<Node> childIterator) {
		int i = 0;
		while (childIterator.hasNext()) {
			CityNode cityChild = (CityNode) childIterator.next();
			node.getChildren().add(new CityNode(cityChild.getCity()));
			i++;
		}
		for (int j = 0; j < i; j++)
			childIterator.previous();
	}

	private void recurseParent(Node parent) {
		final LinkedList<Node> children = (LinkedList<Node>) parent.getChildren();
		ListIterator<Node> childIterator = children.listIterator();
		int bounds = children.size() - 1;
		for (int i = 0; i < bounds ; i++) {
			Node child = childIterator.next();
			if (child.hasChildren())
				recurseParent(child);
			else
				addChildren(child, childIterator);
		}
		childIterator.next();
		childIterator.remove();
	}
}
