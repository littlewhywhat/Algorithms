package com.littlewhywhat.datastructure.divider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSimpleArrayDivider {

	private static final Integer[] ARRAY = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 };	
	private SimpleArrayDivider<Integer> divider;
	private static final int START_INDEX = 2;
	
	@Before
	public void setUp() throws Exception {
		divider = new SimpleArrayDivider<Integer>();
		divider.setArray(ARRAY);
		divider.setNumberOfParts(4);
		divider.setStartIndex(START_INDEX);
	}

	@Test
	public void testGetItem() {
		checkPartFrom(START_INDEX);
	}

	@Test
	public void testGoToPart() {
		divider.goToPart(3);
		checkPartFrom(0);
	}
	
	@Test
	public void testPartHasItems() {
		divider.goToPart(0);
		int count = 0;
		while (divider.partHasItems()) {
			divider.getItem();
			count++;
		}
		Assert.assertEquals(2, count);
	}
	@Test
	public void testSetInterval() {
		divider.setNumberOfParts(3);
		divider.setStartIndex(START_INDEX);
		divider.goToPart(0);
		int count = 0;
		while (divider.partHasItems()) {
			System.out.println(divider.getItem());
			count++;
		}
		Assert.assertEquals(3, count);
		for (int i = 0; i < 3; i++){
			divider.goToPart(i);
			while (divider.partHasItems()) {
				System.out.println(divider.getItem());
			}
		}
			
		
	}
	
	private void checkPartFrom(int index) {
		while(divider.partHasItems()) {
			Assert.assertEquals(ARRAY[index], divider.getItem());
			index++;
		}
	}
	
	

}
