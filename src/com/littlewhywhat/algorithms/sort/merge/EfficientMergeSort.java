package com.littlewhywhat.algorithms.sort.merge;

import com.littlewhywhat.algorithms.sort.merge.SortedPartsChain.SortedPart;

public class EfficientMergeSort extends AbstractMergeSort {

	private SortedPartsChain chain = new SortedPartsChain();

	@Override
	protected void merge(int firstHalfStart, int firstHalfLength,
			int secondHalfStart, int secondHalfLength) {
		if (firstHalfLength == 1 && secondHalfLength == 1) {
			if (getOutput()[firstHalfStart] > getOutput()[secondHalfStart]) {
				int temp = getOutput()[firstHalfStart];
				getOutput()[firstHalfStart] = getOutput()[secondHalfStart];
				getOutput()[secondHalfStart] = temp;
			}
				
		} else {
			chain.addFirst(chain.getNewSortedPart(firstHalfStart,
					firstHalfLength));
			chain.addLast(chain.getNewSortedPart(secondHalfStart,
					secondHalfLength));
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
				chain.removeEmptyParts();
			}
			chain.getFirst().remove();
		}
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
