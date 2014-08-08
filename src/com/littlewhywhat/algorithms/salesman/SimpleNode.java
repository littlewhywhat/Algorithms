package com.littlewhywhat.algorithms.salesman;

import java.util.LinkedList;
import java.util.List;

public class SimpleNode implements LinkedNode {

	private LinkedNode parent;
	private final List<LinkedNode> children = new LinkedList<LinkedNode>();
	
	@Override
	public LinkedNode getParent() {
		return this.parent;
	}

	@Override
	public List<LinkedNode> getChildren() {
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
