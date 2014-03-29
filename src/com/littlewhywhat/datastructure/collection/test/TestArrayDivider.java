package com.littlewhywhat.datastructure.collection.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.Array;
import com.littlewhywhat.datastructure.collection.ArrayDivider;
import com.littlewhywhat.datastructure.test.TestArray;

public class TestArrayDivider {

	private static final int NUMBER_OF_PARTS = 2;

	private static final Integer[] ARRAY_ONE = new Integer[] { 1, 2, 3 };
	private static final Integer[] ARRAY_TWO = new Integer[] { 1, 2, 3, 4 };
	private static final Integer[] ARRAY_THREE = new Integer[] { 1, 2, 3, 4, 5 };

	private ArrayDivider<Integer> divider;

	@Before
	public void setUp() throws Exception {
		divider = ArrayDivider.getInstance(TestArray.ARRAY, NUMBER_OF_PARTS);
	}

	@Test
	public void testGetPart() {
		Array<Integer> part = divider.getPart(0);
		Assert.assertEquals(1, part.size());
		part = divider.getPart(1);
		Assert.assertEquals(2, part.size());
	}

	@Test
	public void testGetPartOne() {
		divider.setArray(ARRAY_ONE);
		divider.setNumberOfParts(3);
		Array<Integer> part = divider.getPart(0);
		Assert.assertEquals(1, part.size());
		part = divider.getPart(1);
		Assert.assertEquals(1, part.size());
		part = divider.getPart(2);
		Assert.assertEquals(1, part.size());
	}

	@Test
	public void testGetPartTwo() {
		divider.setArray(ARRAY_TWO);
		divider.setNumberOfParts(2);
		Array<Integer> part = divider.getPart(0);
		Assert.assertEquals(2, part.size());
		part = divider.getPart(1);
		Assert.assertEquals(2, part.size());

	}

	@Test
	public void testGetPartThree() {
		divider.setArray(ARRAY_THREE);
		divider.setNumberOfParts(3);
		Array<Integer> part = divider.getPart(0);
		Assert.assertEquals(1, part.size());
		part = divider.getPart(1);
		Assert.assertEquals(1, part.size());
		part = divider.getPart(2);
		Assert.assertEquals(3, part.size());
	}

	@Test
	public void testSetStartIndex() {
		divider.setArray(ARRAY_THREE);
		divider.setNumberOfParts(3);
		divider.setStartIndex(2);
		Array<Integer> part = divider.getPart(0);
		Assert.assertEquals(1, part.size());
		Assert.assertEquals(3, part.get(0), 0);

		part = divider.getPart(1);
		Assert.assertEquals(1, part.size());
		Assert.assertEquals(4, part.get(0), 5);

		part = divider.getPart(2);
		Assert.assertEquals(3, part.size());
		Assert.assertEquals(5, part.get(0), 0);
		Assert.assertEquals(1, part.get(1), 0);
		Assert.assertEquals(2, part.get(2), 0);
	}

}
