package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort.ArraySplitterList;
import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort.Splitter;

class SimpleArraySplitterList implements ArraySplitterList {

	private int[] array;
	private SimpleSplitter header;
	private int size = 0;
	private int genId = 0;

	public SimpleArraySplitterList() {
		header = new SimpleSplitter(genId++);
		header.setNext(header);
		header.setPrev(header);
	}

	@Override
	public void setArray(int[] array) {
		this.array = array;
		this.header.setItemId(array.length);
	}

	@Override
	public int sizeCache() {
		return this.genId;
	}

	@Override
	public int size() {
		return this.size;
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
	public Splitter getFirst() {
		if (this.size > 0)
			return (Splitter) this.header.getNext();
		return null;
	}

	@Override
	public Splitter getLast() {
		if (this.size > 0)
			return (Splitter) this.header.getPrev();
		return null;
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
	public void clean() {
		SimpleSplitter splitter = (SimpleSplitter) this.header.getNext();
		int size = this.size;
		for (int i = 0; i < size; i++) {
			SimpleSplitter next = (SimpleSplitter) splitter.getNext();
			if (splitter.getLength() == 0)
				splitter.remove();
			splitter = next;
		}
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

	
	
	// private class SinglyNode {
	// private SinglyNode next;
	//
	// protected SinglyNode getNext() {
	// return next;
	// }

	// protected void setNext(SinglyNode next) {
	// this.next = next;
	// }
	// }

	private class DoublyNode {
		private DoublyNode next;
		private DoublyNode prev;

		protected DoublyNode getPrev() {
			return prev;
		}

		protected void setPrev(DoublyNode prev) {
			this.prev = prev;
		}

		protected DoublyNode getNext() {
			return next;
		}

		protected void setNext(DoublyNode next) {
			this.next = next;
		}

		protected void remove() {
			DoublyNode next = this.next;
			DoublyNode prev = this.prev;
			next.setPrev(prev);
			prev.setNext(next);
			this.next = null;
			this.prev = null;
		}

		protected void addBefore(DoublyNode node) {
			node.setPrev(this.prev);
			node.setNext(this);
			this.prev.setNext(node);
			this.setPrev(node);
		}

		protected void addAfter(DoublyNode node) {
			node.setNext(this.next);
			node.setPrev(this);
			this.next.setPrev(node);
			this.setNext(node);
		}
	}

	class SimpleSplitter extends DoublyNode implements Splitter {

		private int itemId = 0;
		private int id;

		private int getId() {
			return this.id;
		}

		private SimpleSplitter(int id) {
			this.id = id;
		}

		private void setItemId(int itemId) {
			this.itemId = itemId;
		}

		private int getItemId() {
			return this.itemId;
		}

		@Override
		protected void remove() {
			super.remove();
			size--;
		}

		int getLength() {
			return ((SimpleSplitter) getNext()).getItemId() - this.itemId;
		}

		@Override
		public int getItem() {
			return array[itemId];
		}

		@Override
		public int getIndex() {
			return this.itemId;
		}

		@Override
		public Splitter getNextSplitter() {
			SimpleSplitter next = (SimpleSplitter) this.getNext();
			if (next.getId() != 0)
				return next;
			else
				return null;
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
		public int swapItem(int newItem) {
			int oldItem = this.getItem();
			array[itemId] = newItem;
			return oldItem;
		}

		@Override
		public void move() {
			this.itemId++;
		}

		@Override
		public void addBefore(int itemId) {
			SimpleSplitter splitter = new SimpleSplitter(genId++);
			splitter.setItemId(itemId);
			this.addBefore(splitter);
			size++;
		}

		@Override
		public void addAfter(int itemId) {
			SimpleSplitter splitter = new SimpleSplitter(genId++);
			splitter.setItemId(itemId);
			this.addAfter(splitter);
			size++;
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
			if (!(obj instanceof SimpleSplitter)) {
				return false;
			}
			SimpleSplitter other = (SimpleSplitter) obj;
			if (id != other.id) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return "[itemId=" + itemId + ", id=" + id + "]";
		}

	}

}
