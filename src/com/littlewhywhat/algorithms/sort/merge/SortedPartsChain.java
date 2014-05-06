package com.littlewhywhat.algorithms.sort.merge;

import java.util.LinkedList;
import java.util.NoSuchElementException;

class SortedPartsChain {
	private static final String EMPTY_MESSAGE = "SortedPartsChain is empty";
	private static int GEN_ID = 0;
	private Node header;
	private LinkedList<SortedPart> stack;
	private LinkedList<SortedPart> emptyParts;
	private int[] array;

	void setArray(int[] array) {
		this.array = array;
	}

	SortedPartsChain() {
		header = new Node();
		stack = new LinkedList<SortedPart>();
		emptyParts = new LinkedList<SortedPart>();
		header.next = header;
		header.prev = header;
	}

	SortedPart getNext(Node part) {
		if (part.next instanceof SortedPart)
			return (SortedPart) part.next;
		return null;
	}

	void removeEmptyParts() {
		SortedPart emptyPart = emptyParts.pollFirst();
		while (emptyPart != null) {
			emptyPart.remove();
			emptyPart = emptyParts.pollFirst();
		}
	}

	SortedPart getNewSortedPart() {
		if (stack.isEmpty())
			return new SortedPart(GEN_ID++);
		else
			return stack.pop();
	}

	SortedPart getNewSortedPart(int itemId, int length) {
		SortedPart part = getNewSortedPart();
		part.setItemId(itemId);
		part.setLength(length);
		return part;
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
			if (!(part instanceof SortedPart))
				throw new NoSuchElementException();
		}
		return (SortedPart) part;
	}

	SortedPart getFromFirst(int index) {
		int count = 0;
		Node part = this.getFirst();
		while (count != index) {
			count++;
			part = part.getNext();
			if (!(part instanceof SortedPart))
				throw new NoSuchElementException();
		}
		return (SortedPart) part;
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
		return GEN_ID - stack.size();
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
		private int id;

		private SortedPart(int id) {
			this.id = id;
		}

		int getItem() {
			return array[this.itemId];
		}

		int swapItem(int newValue) {
			int old = this.getItem();
			array[this.itemId] = newValue;
			return old;
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

		public void goNext() {
			this.length--;
			this.itemId++;
			if (length == 0)
				emptyParts.push(this);
		}

		public void incrementLength() {
			this.length++;

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SortedPart)) {
				return false;
			}
			SortedPart other = (SortedPart) obj;
			if (id != other.id) {
				return false;
			}
			return true;
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
