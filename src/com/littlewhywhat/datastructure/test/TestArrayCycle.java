package com.littlewhywhat.datastructure.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.ArrayCycle;

public class TestArrayCycle {

	private static final Integer[] ARRAY = new Integer[] { 1, 2, 3 };
	private static final int NUMBER_OF_CYCLES = 5;
	ArrayCycle<Integer> arrayCycle;

	@Before
	public void setUp() throws Exception {
		arrayCycle = new ArrayCycle<Integer>();
		arrayCycle.setArray(ARRAY);
	}

	@Test
	public void testHasNext() {
		int count = 1;
		while (arrayCycle.cycleHasNext()) {
			Assert.assertEquals(count, arrayCycle.next(), 0);
			count++;
		}
	}

	@Test
	public void testNext() {
		int count;
		for (int i = 0; i < NUMBER_OF_CYCLES; i++) {
			count = 1;
			for (int j = 0; j < arrayCycle.size(); j++) {
				Assert.assertEquals(count, arrayCycle.next(), 0);
				count++;
			}
		}
	}

}
