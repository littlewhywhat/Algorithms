package com.littlewhywhat.algorithms.salesman;


public class SimpleNode implements LinkedNode {

	private LinkedNode parent;
	private LinkedNode firstChild;
	private LinkedNode first;
	private LinkedNode last;
	
	@Override
	public LinkedNode getParent() {
		return this.parent;
	}

	@Override
	public boolean hasParent() {
		return this.parent != null;
	}

	@Override
	public boolean hasChildren() {
		return this.firstChild != null;
	}

	@Override
	public LinkedNode getFirstChild() {
		return firstChild;
	}

	@Override
	public LinkedNode getFirst() {
		return this.first;
	}

	@Override
	public LinkedNode getLast() {
		return this.last;
	}

	@Override
	public void addBeforeLast(LinkedNode node) {
		((SimpleNode)node).setFirst(this);
		((SimpleNode)node).setLast(last);
		((SimpleNode)last).setFirst(node);
		((SimpleNode)this).setLast(node);
	}

	@Override
	public void addAfterFirst(LinkedNode node) {
		((SimpleNode)node).setFirst(last);
		((SimpleNode)node).setLast(this);
		((SimpleNode)first).setLast(node);
		((SimpleNode)this).setFirst(node);
	}

	private void setFirst(LinkedNode first) {
		this.first = first;
	}

	private void setLast(LinkedNode last) {
		this.last = last;
	}

}
