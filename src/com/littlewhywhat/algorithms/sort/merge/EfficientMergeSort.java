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
		private static final int LAST_CODE = 0;
		private static final int PRELAST_CODE = 1;
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
			SortedPart part1 = new SortedPart();
			part1.set(index1, length1);
			SortedPart part2 = new SortedPart();
			part2.set(index1 + length1, length2);
			parts.add(part1);
			parts.add(part2);
		}

		public void execute() {
			while (parts.size() != 1) {
				int partCode = getMin();
				int min = getLastOrPreLast(partCode).getItem();
				int partIndex = 0;
				SortedPart part = parts.get(partIndex);
				int swap = part.swapItem(min);
				part.goNext();
				while (swap != min) {
					partIndex += 2;
					if (!(partIndex < parts.size() - partCode))
						addEmptyPart(partCode);
					part = parts.get(partIndex);
					swap = part.swapItem(swap);
					part.goNext();
					parts.get(partIndex - 1).length++;
				}
				removeEmptyParts();
			}
			this.parts.pollFirst();
		}

		private SortedPart getLastOrPreLast(int partCode) {
			SortedPart toReturn;
			SortedPart last = parts.pollLast();
			if (partCode == LAST_CODE)
				toReturn = last;
			else
				toReturn = parts.getLast();
			parts.addLast(last);
			return toReturn;
		}

		private void addEmptyPart(int partCode) {
			SortedPart part = new SortedPart();
			if (partCode == PRELAST_CODE) {
				SortedPart last = parts.pollLast();
				SortedPart preLast = parts.pollLast();
				part.itemId = preLast.itemId;
				parts.addLast(part);
				parts.addLast(preLast);
				parts.addLast(last);
			}
			else {
				SortedPart last = parts.pollLast();
				part.itemId = last.itemId;
				parts.addLast(part);
				parts.addLast(last);
			}
		}

		private void removeEmptyParts() {
			int size = parts.size();
			for (int i = 0; i < size; i++) {
				SortedPart part = parts.pollFirst();
				if (part.length != 0)
					parts.addLast(part);
				else
					emptyParts.push(part);
			}
		}
		
		private int getMin() {
			SortedPart last = parts.getLast();
			SortedPart preLast = parts.get(parts.size() - 2);
			if (last.getItem() < preLast.getItem())
				return LAST_CODE;
			else
				return PRELAST_CODE;
		}

		private class SortedPart {
			private int id;
			private int itemId;
			private int length;

			private SortedPart() {
				this.id = GEN_SORTED_PART_ID;
				GEN_SORTED_PART_ID++;
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
