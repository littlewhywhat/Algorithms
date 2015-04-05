package com.littlewhywhat.algorithms.salesman;

public interface LinkedNode {
	LinkedNode getPrevious();
	LinkedNode getNext();
	boolean hasNext();
	boolean hasPrevious();
}
