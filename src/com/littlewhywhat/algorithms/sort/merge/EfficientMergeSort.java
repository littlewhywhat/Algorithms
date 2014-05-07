package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.sort.merge.SortedPartsChain.SortedPart;

public class EfficientMergeSort extends AbstractMergeSort {

	private SortedPartsChain chain = new SortedPartsChain();

	public int getGenId() {
		return chain.getGENID();
	}

	@Override
	protected void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		int sumLength = firstHalfLength + secondHalfLength;
		if (sumLength < 400)
			insertionSort(firstHalfStart, firstHalfStart + sumLength);
		else
			mergeSort(firstHalfStart, firstHalfLength, secondHalfStart,
					secondHalfLength);

	}

	private void insertionSort(int start, int end) {
		int[] array = getOutput();
		for (int j = start + 1; j < end; j++) {
			int newItem = array[j];
			int i = j - 1;
			while (i > start - 1 && newItem < array[i]) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = newItem;
		}
	}

	private void mergeSort(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		chain.addFirst(chain.getNewSortedPart(firstHalfStart, firstHalfLength));
		chain.addLast(chain.getNewSortedPart(secondHalfStart, secondHalfLength));
		while (!chain.oneRemained()) {

			SortedPart minPart = getMinLastOrPrelast();
			int min = minPart.getItem();
			SortedPart part = chain.getFirst();
			int swap = part.swapItem(minPart.getItem());
			SortedPart nextpart;
			SortedPart nextnextpart;
			part.goNext();
			while (swap != min) {
				nextpart = chain.getNext(part);
				nextnextpart = chain.getNext(nextpart);
				if (nextnextpart == null
						|| (nextnextpart.equals(chain.getLast()) && minPart
								.equals(chain.getFromLast(1)))) {
					SortedPart emptyPart = chain.getNewSortedPart();
					emptyPart.setItemId(nextpart.getItemId());
					nextpart.addBefore(emptyPart);
					nextnextpart = nextpart;
					nextpart = emptyPart;
				}
				part = nextnextpart;
				swap = part.swapItem(swap);
				part.goNext();
				nextpart.incrementLength();
			}
			// if (chain.size() > 1) {
			// System.out.println(chain);
			// for (int i = chain.getFirst().getItemId(); i <
			// chain.getLast().getItemId() + chain.getLast().getLength() - 1;
			// i++)
			// System.out.print(getOutput()[i] + " ");
			// System.out.println();
			// }
			chain.removeEmptyParts();
		}
		chain.getFirst().remove();
	}

	private SortedPart getMinLastOrPrelast() {
		SortedPart last = chain.getLast();
		SortedPart preLast = chain.getFromLast(1);
		if (last.getItem() > preLast.getItem())
			return preLast;
		return last;
	}

	@Override
	protected void setup() {
		super.setup();
		chain.setArray(getOutput());
	}
}
