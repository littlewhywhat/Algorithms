package com.littlewhywhat.algorithms.salesman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		cache = new CityNode(cities.get(0));
		current = new CityNode(cities.get(0));
		for (int index = 1; index < cities.size(); index++)
			cache.getChildren().add(new CityNode(cities.get(index)));
		//printTree(cache, "");
		for (int m = 2; m < cities.size(); m++) {
			recurseParent(current, (LinkedList<Node>) cache.getChildren());
			//printTree(current, "");
			changeCacheCurrent();
		}
	}

	private void changeCacheCurrent() {
		Node temp = cache;
		cache = current;
		current = temp;
	}
	
	private void printTree(Node node, String tab) {
		System.out.println(tab + node);
		if (node.hasChildren()) {
			String extraTab = tab + ">> ";
			for (Node cityNode : node.getChildren()) 
				printTree(cityNode, extraTab);
		}
	}

	private void addChildren(Node node, List<Node> children) {
		for (Node child : children) {
			node.getChildren().add(new CityNode(((CityNode) child).getCity()));
		}
	}

	private void recurseParent(Node futureParent, LinkedList<Node> children) {
		while (children.size() != 1) {
			CityNode child = (CityNode) children.poll();
			CityNode newChild = new CityNode(child.getCity());
			futureParent.getChildren().add(newChild);
			if (child.hasChildren())
				recurseParent(newChild, (LinkedList<Node>) child.getChildren());
			else
				addChildren(newChild, children);
		}
		children.poll();
	}
}
