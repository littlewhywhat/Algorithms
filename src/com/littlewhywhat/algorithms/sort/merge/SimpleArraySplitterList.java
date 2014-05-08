package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort.ArraySplitterList;
import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort.Splitter;

class SimpleArraySplitterList implements ArraySplitterList {

	private class DoublyNode {
		private DoublyNode next;
		private DoublyNode prev;

		protected void addAfter(DoublyNode node) {
			node.setNext(this.next);
			node.setPrev(this);
			this.next.setPrev(node);
			this.setNext(node);
		}

		protected void addBefore(DoublyNode node) {
			node.setPrev(this.prev);
			node.setNext(this);
			this.prev.setNext(node);
			this.setPrev(node);
		}

		protected DoublyNode getNext() {
			return next;
		}

		protected DoublyNode getPrev() {
			return prev;
		}

		protected void remove() {
			DoublyNode next = this.next;
			DoublyNode prev = this.prev;
			next.setPrev(prev);
			prev.setNext(next);
			this.next = null;
			this.prev = null;
		}

		protected void setNext(DoublyNode next) {
			this.next = next;
		}

		protected void setPrev(DoublyNode prev) {
			this.prev = prev;
		}
	}
	class SimpleSplitter extends DoublyNode implements Splitter {

		private DoublyNode nextToDelete;
		private int itemId = 0;
		private int id;

		private SimpleSplitter(int id) {
			this.id = id;
		}

		@Override
		public void addAfter(int itemId) {
			SimpleSplitter splitter = getFreshSimpleSplitter();
			splitter.setItemId(itemId);
			this.addAfter(splitter);
			size++;
		}

		@Override
		public void addBefore(int itemId) {
			SimpleSplitter splitter = getFreshSimpleSplitter();
			splitter.setItemId(itemId);
			this.addBefore(splitter);
			size++;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SimpleSplitter)) {
				return false;
			}
			SimpleSplitter other = (SimpleSplitter) obj;
			if (id != other.id) {
				return false;
			}
			return true;
		}

		private int getId() {
			return this.id;
		}

		@Override
		public int getIndex() {
			return this.itemId;
		}

		@Override
		public int getItem() {
			return array[itemId];
		}

		private int getItemId() {
			return this.itemId;
		}

		int getLength() {
			return ((SimpleSplitter) getNext()).getItemId() - this.itemId;
		}

		@Override
		public Splitter getNextSplitter() {
			SimpleSplitter next = (SimpleSplitter) this.getNext();
			if (next.getId() != 0)
				return next;
			else
				return null;
		}

		private DoublyNode getNextToDelete() {
			return this.nextToDelete;
		}

		@Override
		public Splitter getPrevSplitter() {
			SimpleSplitter prev = (SimpleSplitter) this.getPrev();
			if (prev.getId() != 0)
				return prev;
			else
				return null;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public void move() {
			this.itemId++;
			if (getLength() == 0)
				pushToDelete(this);
		}

		@Override
		protected void remove() {
			super.remove();
			size--;
			pushToFresh(this);
		}

		private void setItemId(int itemId) {
			this.itemId = itemId;
		}

		private void setNextToDelete(DoublyNode nextToDelete) {
			this.nextToDelete = nextToDelete;
		}

		@Override
		public int swapItem(int newItem) {
			int oldItem = this.getItem();
			array[itemId] = newItem;
			return oldItem;
		}

		@Override
		public String toString() {
			return "[itemId=" + itemId + ", id=" + id + "]";
		}

	}
	private int[] array;
	private SimpleSplitter header;
	private DoublyNode deleteHeader;
	private DoublyNode freshHeader;

	private int size = 0;

	private int genId = 0;

	public SimpleArraySplitterList() {
		deleteHeader = new DoublyNode();
		deleteHeader.setNext(deleteHeader);
		freshHeader = new DoublyNode();
		freshHeader.setNext(freshHeader);

		header = getFreshSimpleSplitter();
		header.setNext(header);
		header.setPrev(header);

	}

	@Override
	public void addFirst(int itemId) {
		this.header.addAfter(itemId);
	}

	@Override
	public void addLast(int itemId) {
		this.header.addBefore(itemId);
	}

	@Override
	public void clean() {
		DoublyNode toDelete = deleteHeader.getNext();
		while (toDelete instanceof SimpleSplitter) {
			SimpleSplitter splitter = (SimpleSplitter) toDelete;
			toDelete = splitter.getNextToDelete();
			splitter.remove();
		}
		deleteHeader.setNext(null);
	}

	@Override
	public Splitter get(int index) {
		if (index < 0 || !(index < this.size))
			return null;
		SimpleSplitter splitter = this.header;
		if (index < (this.size / 2))
			while (index > -1) {
				splitter = (SimpleSplitter) splitter.getNext();
				index--;
			}
		else
			while (index < this.size) {
				splitter = (SimpleSplitter) splitter.getPrev();
				index++;
			}
		return splitter;
	}

	@Override
	public Splitter getFirst() {
		if (this.size > 0)
			return (Splitter) this.header.getNext();
		return null;
	}

	private SimpleSplitter getFreshSimpleSplitter() {
		if (this.freshHeader.getNext() instanceof SimpleSplitter)
			return pullFromFresh();
		return new SimpleSplitter(genId++);
	}

	@Override
	public Splitter getLast() {
		if (this.size > 0)
			return (Splitter) this.header.getPrev();
		return null;
	}

	private SimpleSplitter pullFromFresh() {
		SimpleSplitter fresh = (SimpleSplitter) this.freshHeader.getNext();
		this.freshHeader.setNext(fresh.getNextToDelete());
		fresh.setNextToDelete(null);
		return fresh;
	}

	private void pushToDelete(SimpleSplitter splitter) {
		splitter.setNextToDelete(this.deleteHeader.getNext());
		this.deleteHeader.setNext(splitter);
	}

	private void pushToFresh(SimpleSplitter splitter) {
		splitter.setNextToDelete(this.freshHeader.getNext());
		this.freshHeader.setNext(splitter);
	}

	@Override
	public void remove(int index) {
		((SimpleSplitter) this.get(index)).remove();
	}

	@Override
	public void removeAll() {
		int size = this.size;
		for (int i = 0; i < size; i++)
			this.remove(0);
	}

	@Override
	public void setArray(int[] array) {
		this.array = array;
		this.header.setItemId(array.length);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int sizeCache() {
		return this.genId;
	}

	@Override
	public String toString() {
		String string = "[";
		SimpleSplitter splitter = (SimpleSplitter) this.header.getNext();
		for (int i = 0; i < this.size; i++) {
			string += splitter.toString() + ',';
			splitter = (SimpleSplitter) splitter.getNext();
		}
		if (string.length() > 1)
			string = string.substring(0, string.length() - 1);
		string = string + "]";
		return string;
	}

}
