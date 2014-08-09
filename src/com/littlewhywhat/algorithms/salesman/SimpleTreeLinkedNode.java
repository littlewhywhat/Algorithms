package com.littlewhywhat.algorithms.salesman;

public class SimpleTreeLinkedNode extends SimpleLinkedNode implements TreeLinkedNode {

	private LinkedList<TreeLinkedNode> children = new SimpleLinkedList();
	private TreeLinkedNode parent;
	
	@Override
	public LinkedList<TreeLinkedNode> getChildren() {
		return children;
	}

	@Override
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	public TreeLinkedNode getParent() {
		return parent;
	}

	@Override
	public boolean hasParent() {
		return parent != null;
	}

	@Override
	public void setPrevious(TreeLinkedNode node) {
		previous = node;
	}

	@Override
	public void setNext(TreeLinkedNode node) {
		next = node;
	}

}
