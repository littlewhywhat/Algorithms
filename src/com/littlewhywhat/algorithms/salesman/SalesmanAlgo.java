package com.littlewhywhat.algorithms.salesman;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgo extends AbstractAlgorithm<Void, List<City>, Double> {

	private class CityNode extends SimpleNode {
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

	private LinkedNode current;

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

	private void printTree(LinkedNode node, String tab) {
		String str = ((CityNode)node).map != null? ((CityNode)node).map.toString(): "";
		System.out.println(tab + node + str);
		if (node.hasChildren()) {
			String extraTab = tab + ">> ";
			for (LinkedNode cityNode : node.getChildren()) 
				printTree(cityNode, extraTab);
		}
	}

	private void addChildren(LinkedNode node, ListIterator<LinkedNode> childIterator) {
		int i = 0;
		while (childIterator.hasNext()) {
			CityNode cityChild = (CityNode) childIterator.next();
			CityNode newCityChild = new CityNode(cityChild.getCity());
			node.getChildren().add(newCityChild);
			i++;
			
		}
		for (int j = 0; j < i; j++)
			childIterator.previous();
	}

	private void recurseParent(LinkedNode parent) {
		((CityNode)parent).map = null;
		final LinkedList<LinkedNode> children = (LinkedList<LinkedNode>) parent.getChildren();
		ListIterator<LinkedNode> childIterator = children.listIterator();
		int bounds = children.size() - 1;
		for (int i = 0; i < bounds ; i++) {
			LinkedNode child = childIterator.next();
			((CityNode)child).map = null;
			if (child.hasChildren())
				recurseParent(child);
			else
				addChildren(child, childIterator);
		}
		childIterator.next();
		childIterator.remove();
	}
}
