package com.littlewhywhat.algorithms.sort.merge;

import java.util.LinkedList;
import java.util.Stack;

public class EfficientMergeSort extends AbstractMergeSort {

	private Merger merger = new Merger();

	@Override
	protected void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		merger.init(firstHalfStart, firstHalfLength, secondHalfLength);
		merger.setArray(getOutput());
		merger.execute();
	}

	public static class Merger {
		private byte GEN_SORTED_PART_ID = 0;
		private static final int MAX_COUNT_PARTS = 4;
		private Stack<SortedPart> emptyParts = new Stack<SortedPart>();
		private LinkedList<SortedPart> parts = new LinkedList<SortedPart>();
		private int[] array;

		public Merger() {
			for (int i = 0; i < MAX_COUNT_PARTS; i++)
				emptyParts.push(new SortedPart());
		}

		public void setArray(int[] array) {
			this.array = array;
		}

		public void init(int index1, int length1, int length2) {
			parts.add(getFreshPart(index1, length1));
			parts.add(getFreshPart(index1 + length1, length2));
		}

		private void removePart(SortedPart sortedPart) {
			parts.remove(sortedPart);
			emptyParts.push(sortedPart);
		}

		public void execute() {
			while (parts.size() != 1) {
				switch (parts.size()) {
				case (2):
					// System.out.println("case2");
					if (!minIsLast()) {
						parts.getFirst().goNext();
						break;
					}
					swapLast();

					if (!parts.getLast().stayedSorted())
						addPart();
					parts.getFirst().goNext();
					break;
				case (3):
					// System.out.println("case3");
					if (minIsLast()) {
						swapLast();
						parts.getFirst().goNext();
						parts.getLast().goNext();
						getPreLast().length++;
						break;
					}
					swapPreLast();
					addPart();
					parts.getFirst().goNext();

					break;
				case (4):
					if (minIsLast()) {
						swapLast();
						parts.getFirst().goNext();
						parts.getLast().goNext();
						getPreLast().length++;
						break;
					}
					swapPreLast();
					parts.getFirst().goNext();
					parts.get(1).length++;
					getPreLast().goNext();
					break;
				}
				int size = parts.size();
				for (int i = 0; i < size; i++) {
					SortedPart part = parts.pollFirst();
					if (part.length != 0)
						parts.addLast(part);
					else
						emptyParts.push(part);
				}

			}
			removePart(this.parts.getFirst());
		}

		private void addPart() {
			int indexInParts = 1;
			SortedPart rightNeighbour = parts.get(indexInParts);
			int newIndex = rightNeighbour.itemId;
			SortedPart freshPart = getFreshPart(newIndex, 1);
			rightNeighbour.goNext();
			parts.add(indexInParts, freshPart);
		}

		private void swapLast() {
			int currentFirst = this.parts.getFirst().getItem();
			this.parts.getFirst().swapItem(
					this.parts.getLast().swapItem(currentFirst));
		}

		private void swapPreLast() {
			int currentFirst = parts.getFirst().getItem();
			this.parts.getFirst().swapItem(getPreLast().swapItem(currentFirst));
		}

		private SortedPart getPreLast() {
			return parts.get(parts.size() - 2);
		}

		private boolean minIsLast() {
			SortedPart last = parts.getLast();
			SortedPart preLast = parts.get(parts.size() - 2);
			return last.getItem() < preLast.getItem();
		}

		private SortedPart getFreshPart(int index, int length) {
			SortedPart part = emptyParts.pop();
			part.set(index, length);
			return part;
		}

		private class SortedPart {
			private int id;
			private int itemId;
			private int length;

			private SortedPart() {
				this.id = GEN_SORTED_PART_ID;
				GEN_SORTED_PART_ID++;
			}

			public boolean stayedSorted() {
				if (length != 1)
					return array[this.itemId + 1] > array[this.itemId];
				else
					return true;
			}

			public void set(int index, int length) {
				this.itemId = index;
				this.length = length;
			}

			public void goNext() {
				this.length--;

				this.itemId++;
			}

			public int getItem() {
				return array[this.itemId];
			}

			public int swapItem(int value) {
				int old = this.getItem();
				array[this.itemId] = value;
				return old;
			}

			@Override
			public int hashCode() {
				return id;
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

			@Override
			public String toString() {
				return "[id=" + itemId + ", len=" + length + "]";
			}
		}
	}
}
