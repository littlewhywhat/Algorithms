package com.littlewhywhat.algorithms.salesman;


public interface LinkedNode {
	boolean hasParent();
	LinkedNode getParent();
	boolean hasChildren();
	LinkedNode getFirstChild();
	LinkedNode getFirst();
	LinkedNode getLast();
	void addBeforeLast(LinkedNode node);
	void addAfterFirst(LinkedNode node);
}
 