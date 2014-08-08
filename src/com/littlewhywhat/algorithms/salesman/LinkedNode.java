package com.littlewhywhat.algorithms.salesman;

import java.util.List;

public interface LinkedNode {
	boolean hasParent();
	LinkedNode getParent();
	boolean hasChildren();
	List<LinkedNode> getChildren();
	LinkedNode getFirstChild();
	LinkedNode getFirst();
	LinkedNode getLast();
}
 