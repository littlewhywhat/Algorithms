package com.littlewhywhat.algorithms.salesman;

public interface TreeLinkedNode extends LinkedNode {
	LinkedList<TreeLinkedNode> getChildren();
	boolean hasChildren();
	TreeLinkedNode getParent();
	boolean hasParent();
	void setPrevious(TreeLinkedNode node);
	void setNext(TreeLinkedNode node);
}
