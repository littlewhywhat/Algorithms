package com.littlewhywhat.algorithms.sort.merge;

public class EfficientMergeSort extends AbstractMergeSort {

	private Merger merger = new Merger();
	
	@Override
	protected void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		merger.setArray(getOutput());
		merger.setParts(firstHalfLength, firstHalfStart, secondHalfLength,
				secondHalfStart);
		merger.execute();
	}

	public static class Merger {

		private int[] array;
		private SortedPart[] parts = new SortedPart[] { new SortedPart(),
				new SortedPart(), new SortedPart(), new SortedPart() };
		private int comparePartOne;
		private int comparePartTwo;

		private void setArray(int[] array) {
			this.array = array;
			for (SortedPart part : parts)
				part.setArray(array);
		}

		private int[] getArray() {
			return this.array;
		}

		public void execute() {
			while (parts[0].length != 0 && parts[3].length != 0) {
				//System.out.println(parts[0] + " " + parts[3]);
				int minResult = min(0, 3);
				if (minResult == 0) {
					parts[0].goNext();
					continue;
				}
				swap(minResult);
				parts[0].goNext();
				if (parts[3].isSorted())
					continue;
				switchToThree();
			}
		}

		public void setParts(int lengthOne, int indexOne, int lengthTwo,
				int indexTwo) {
			parts[0].setIndex(indexOne);
			parts[0].setLength(lengthOne);
			parts[3].setIndex(indexTwo);
			parts[3].setLength(lengthTwo);
			comparePartOne = 0;
			comparePartTwo = 3;
		}

		private int min(int indexOne, int indexTwo) {
			if (getItem(indexOne) > getItem(indexTwo))
				return indexTwo;
			else
				return indexOne;
		}

		private void swap(int partIndex) {
			int temp = getItem(partIndex);
			setItem(partIndex, getItem(0));
			setItem(0, temp);
		}

		private void switchToThree() {
			comparePartOne = 2;
			parts[2].setIndex(parts[3].getIndex());
			parts[2].setLength(1);
			parts[3].goNext();
			while (parts[0].length != 0 && parts[2].length != 0
					&& parts[3].length != 0) {
				int minResult = min(2, 3);
				swap(minResult);
				if (minResult == 3) {
					parts[2].incrementLength();
					parts[0].goNext();
					parts[3].goNext();
					continue;
				}
				parts[0].goNext();
				switchToFour();
			}
			backToTwo();
		}

		private void backToTwo() {
			if (parts[0].getLength() == 0)
				parts[0].copyFrom(parts[2]);
			if (parts[3].getLength() == 0)
				parts[3].copyFrom(parts[2]);
			comparePartOne = 0;
			comparePartTwo = 3;
		}

		private void switchToFour() {
			parts[1].setIndex(parts[2].getIndex());
			parts[1].setLength(1);
			parts[2].goNext();

			while (parts[0].length != 0 && parts[2].length != 0
					&& parts[3].length != 0) {
				int minResult = min(2, 3);
				swap(minResult);
				if (minResult == 3) {
					parts[2].incrementLength();
					parts[0].goNext();
					parts[3].goNext();
					continue;
				}
				parts[0].goNext();
				parts[2].goNext();
				parts[1].incrementLength();
			}
			backToThree();
		}

		private void backToThree() {
			if (parts[0].getLength() == 0)
				parts[0].copyFrom(parts[1]);
			if (parts[3].getLength() == 0) {
				parts[3].copyFrom(parts[2]);
				parts[2].copyFrom(parts[1]);
			}
			if (parts[2].getLength() == 0)
				parts[2].copyFrom(parts[1]);
			comparePartOne = 2;
			comparePartTwo = 3;
		}

		private int getItem(int partIndex) {
			return getArray()[parts[partIndex].getIndex()];
		}

		private void setItem(int partIndex, int value) {
			getArray()[parts[partIndex].getIndex()] = value;
		}

		public static class SortedPart {
			private int length;
			private int index;
			private int[] array;

			public SortedPart() {
			};

			private void setArray(int[] array) {
				this.array = array;
			}

			public int getLength() {
				return length;
			}

			public int getIndex() {
				return index;
			}

			public void setLength(int length) {
				this.length = length;
			}

			public void setIndex(int index) {
				this.index = index;
			}

			public void goNext() {
				this.length--;
				this.index++;
			}

			public void incrementLength() {
				this.length++;
			}

			public boolean isSorted() {
				if (this.length > 1)
					return this.array[this.index] < this.array[this.index + 1];
				else
					return true;
			}
			
			public void copyFrom(SortedPart part) {
				this.length = part.getLength();
				this.index = part.getIndex();
			}
			
			@Override
			public String toString() {
				return "[len=" + length + ", ind=" + index
						+ "]";
			}
			
			
		}
	}
}
