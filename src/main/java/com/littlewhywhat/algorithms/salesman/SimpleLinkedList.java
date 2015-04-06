package com.littlewhywhat.algorithms.salesman;

public class SimpleLinkedList implements LinkedList<TreeLinkedNode> {

	private TreeLinkedNode first;
	private TreeLinkedNode last;
	
	@Override
	public void removeLast() {
		if (!isEmpty()) {
			TreeLinkedNode oldLast = last;
			last = (TreeLinkedNode) last.getPrevious();
			last.setNext(null);
			oldLast.setPrevious(null);
		}
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public void add(TreeLinkedNode node) {
		if (isEmpty()) {
			first = node;
			last = node;
		}
		else {
			last.setNext(node);
			node.setPrevious(last);
			node.setNext(null);
			last = node;
		}
	}

	@Override
	public TreeLinkedNode getFirst() {
		return first;
	}


}
