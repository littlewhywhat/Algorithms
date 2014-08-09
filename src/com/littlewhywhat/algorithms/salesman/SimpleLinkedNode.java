package com.littlewhywhat.algorithms.salesman;

public class SimpleLinkedNode implements LinkedNode {

	protected LinkedNode previous;
	protected LinkedNode next;
	
	@Override
	public LinkedNode getPrevious() {
		return previous;
	}

	@Override
	public LinkedNode getNext() {
		return next;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public boolean hasPrevious() {
		return previous != null;
	}

}
