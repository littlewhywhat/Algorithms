package com.littlewhywhat.datastructure.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.ArrayCycleStarter;

public class TestArrayCycleStarter {

	private ArrayCycleStarter<Integer> start;
	private static final Integer[] TEST_ARRAY = new Integer[] { 2, 3, 1 };

	@Before
	public void setUp() throws Exception {
		start = new ArrayCycleStarter<Integer>();
		start.setArray(TestArray.ARRAY);
	}

	@Test
	public void testCycleHasNext() {
		int count = 0;
		start.setStartIndex(1);
		while(start.cycleHasNext()) {
			Assert.assertEquals(TEST_ARRAY[count], start.next());
			count++;
		}
	}

	@Test
	public void testReset() {
		start.setStartIndex(2);
		start.next();
		Assert.assertEquals(1, start.next(), 0);
		start.reset();
		Assert.assertEquals(3, start.next(), 0);
	}

	@Test
	public void testSetStartIndex() {
		int count = 1;
		start.setStartIndex(0);
		while(start.cycleHasNext()) {
			Assert.assertEquals(count, start.next(), 0);
			count++;
		}
	}

}
