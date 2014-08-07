package com.littlewhywhat.algorithms.salesman;

import java.util.List;

public interface Node {
	boolean hasParent();
	Node getParent();
	boolean hasChildren();
	List<Node> getChildren();
}
 