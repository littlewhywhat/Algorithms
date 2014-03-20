package com.littlewhywhat.algorithms.cakedivider.test;

import org.junit.Assert;
import org.junit.Test;

import com.littlewhywhat.algorithms.cakedivider.IndicesTracker;

public class TestIndicesTracker {

	private IndicesTracker tracker;
	private static final int QUANTITY = 8;
	private static final int DIVIDER = 4;
	private static final int INTERVAL = QUANTITY / DIVIDER;
	
	@Test
	public void test() {
		tracker = new IndicesTracker(QUANTITY, DIVIDER);
		int i = 0;
		while (tracker.hasNext()) {
			checkIndices(tracker.goNext(), i, INTERVAL);
			i++;
		}
	}
	
	@Test
	public void testGoToPrevious() {
		tracker = new  IndicesTracker(QUANTITY, DIVIDER);
		while (tracker.hasNext()) {
			tracker.goNext();
		}
		checkIndices(tracker.goPrevious(), INTERVAL - 2, INTERVAL);
	}
	
	@Test
	public void testGoToBeginning() {
		tracker = new  IndicesTracker(QUANTITY, DIVIDER);
		while (tracker.hasNext()) {
			tracker.goNext();
		}
		tracker.goToBeginning();
		checkIndices(tracker.goNext(), 0, INTERVAL);
	}
	
	@Test
	public void testHasNext() {
		tracker = new  IndicesTracker(QUANTITY, DIVIDER);
		while (tracker.hasNext()) {
			tracker.goNext();
		}
		checkIndices(tracker.getIndices(), INTERVAL - 1, INTERVAL);
	}
	
	private void checkIndices(int[] indices, int start, int interval) {
		Assert.assertEquals(start , indices[0]);
		start += interval;
		Assert.assertEquals(start , indices[1]);
		start += interval ;
		Assert.assertEquals(start , indices[2]);
		start += interval;
		Assert.assertEquals(start , indices[3]);
	}

}
