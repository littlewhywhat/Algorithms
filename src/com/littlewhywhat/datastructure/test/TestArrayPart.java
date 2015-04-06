package com.littlewhywhat.datastructure.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.ArrayPart;

public class TestArrayPart {

	private Integer[] array;
	private ArrayPart<Integer> part;
	
	@Before
	public void setUp() throws Exception {
		array = TestArray.ARRAY.clone();
		part = ArrayPart.getInstance(array, 1, 1);
	}

	@Test
	public void testSize() {
		Assert.assertEquals(1, part.size(), 0);
	}

	@Test
	public void testGet() {
		Assert.assertEquals(2, part.get(0), 0);
	}

	@Test
	public void testSet() {
		part.set(0, 1);
		Assert.assertEquals(1, array[1], 0);
	}
	
	@Test
	public void testIsOnBorder() {
		part.setStartIndex(2);
		part.setEndIndex(0);
		Assert.assertEquals( array[2], part.get(0), 0);
		Assert.assertEquals( array[0], part.get(1), 0);
		Assert.assertEquals( array[1], part.get(2), 0);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testIterator() {
		part.setStartIndex(2);
		part.setEndIndex(0);
		int count = 0;
		for (Integer element : part)
			count++;
		Assert.assertEquals(count, 2, 0);
		part.setStartIndex(0);
		part.setEndIndex(2);
		count = 0;
		for (Integer element : part)
			count++;
		Assert.assertEquals(count, 3, 0);
	}

}
