package com.littlewhywhat.algorithms.salesman;

import java.util.LinkedList;
import java.util.List;

public class SimpleNode implements Node {

	private Node parent;
	private final List<Node> children = new LinkedList<Node>();
	
	@Override
	public Node getParent() {
		return this.parent;
	}

	@Override
	public List<Node> getChildren() {
		return this.children;
	}

	@Override
	public boolean hasParent() {
		return this.parent != null;
	}

	@Override
	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

}
