package com.littlewhywhat.algorithms.sort.merge;

import java.util.NoSuchElementException;
import java.util.Stack;

class SortedPartsChain {
	private static final String EMPTY_MESSAGE = "SortedPartsChain is empty";
	private Node header;
	private Stack<SortedPart> stack;
	private int[] array;
	
	void setArray(int[] array) {
		this.array = array;
	}
	
	SortedPartsChain() {
		header = new Node();
		stack = new Stack<SortedPart>();
		header.next = header;
		header.prev = header;
	}

	SortedPart getNewSortedPart() {
		if (stack.isEmpty())
			return new SortedPart();
		else
			return stack.pop();
	}

	private void addBetween(Node partPrev, Node partNext, Node part) {
		partPrev.setNext(part);
		partNext.setPrev(part);
		part.setPrev(partPrev);
		part.setNext(partNext);
	}

	void addFirst(SortedPart part) {
		addBetween(header, header.getNext(), part);
	}

	void addLast(SortedPart part) {
		addBetween(header.getPrev(), header, part);
	}

	SortedPart getFromLast(int index) {
		int count = 0;
		Node part = this.getLast();
		while (count != index) {
			count++;
			part = part.getPrev();
			if (!(part instanceof SortedPart ))
				throw new NoSuchElementException();
		}
		return (SortedPart)part;
	}
	
	SortedPart getFromFirst(int index) {
		int count = 0;
		Node part = this.getFirst();
		while (count != index) {
			count++;
			part = part.getNext();
			if (!(part instanceof SortedPart ))
				throw new NoSuchElementException();
		}
		return (SortedPart)part;
	}
	
	SortedPart getLast() {
		try {
			return (SortedPart) header.getPrev();
		} catch (ClassCastException ex) {
			throw new NoSuchElementException(EMPTY_MESSAGE);
		}
	}

	SortedPart getFirst() {
		try {
			return (SortedPart) header.getNext();
		} catch (ClassCastException ex) {
			throw new NoSuchElementException(EMPTY_MESSAGE);
		}
	}

	int size() {
		int size = 0;
		Node node = header;
		while (node.getNext() instanceof SortedPart) {
			node = node.getNext();
			size++;
		}
		return size;
	}

	boolean oneRemained() {
		return (header.getNext() instanceof SortedPart && !(header.getNext()
				.getNext() instanceof SortedPart));
	}

	class Node {
		private Node next;
		private Node prev;

		private Node() {
		};

		private Node getNext() {
			return next;
		}

		private void setNext(Node next) {
			this.next = next;
		}

		private Node getPrev() {
			return prev;
		}

		private void setPrev(Node prev) {
			this.prev = prev;
		}

		void addBefore(Node node) {
			Node prev = this.prev;
			prev.setNext(node);
			this.prev = node;
			node.setNext(this);
			node.setPrev(prev);
		}
		
		void remove() {
			Node prev = this.prev;
			Node next = this.next;
			prev.setNext(this.next);
			next.setPrev(this.prev);
		}
	}

	class SortedPart extends Node {
		private int length;
		private int itemId;
		
		int getItem() {
			return array[this.itemId];
		}
		
		@Override
		void remove() {
			super.remove();
			this.length = 0;
			this.itemId = 0;
			stack.push(this);
		}
		
		
		int getLength() {
			return length;
		}

		void setLength(int length) {
			this.length = length;
		}

		int getItemId() {
			return itemId;
		}

		void setItemId(int itemId) {
			this.itemId = itemId;
		}

		@Override
		public String toString() {
			return "[id=" + itemId + ", len=" + length + "]";
		}

	}

	@Override
	public String toString() {
		Node node = header.next;
		String output = "[";
		while (node instanceof SortedPart) {
			SortedPart part = (SortedPart) node;
			output += part.toString() + ",";
			node = node.getNext();
		}
		if (output.endsWith(","))
			output = output.substring(0, output.length() - 1);
		output += "]";
		return output;
	}

}
