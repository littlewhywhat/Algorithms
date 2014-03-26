package com.littlewhywhat.datastructure.sieve;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestSerialArraySeive {

	private SerialArraySieve<Integer> seive = new SerialArraySieve<Integer>();
	private static final Integer[] ARRAY = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 };
	private static final int INTERVAL = 2;
	
	@Before
	public void setUp() throws Exception {
		seive.setArray(ARRAY);
		seive.setInterval(INTERVAL);
	}

	@Test
	public void testGetItem() {
		seive.reset();
		Assert.assertEquals(1, seive.getItem().intValue());
		Assert.assertEquals(3, seive.getItem().intValue());
		Assert.assertEquals(5, seive.getItem().intValue());
		Assert.assertEquals(7, seive.getItem().intValue());
		Assert.assertEquals(2, seive.getItem().intValue());		
	}

	@Test
	public void testHasItems() {
		seive.reset();
		int count = 0;
		while (seive.hasItems()) {
			System.out.println(seive.getItem());
			count++;
		}
		Assert.assertEquals(8, count);
	}

	@Test
	public void testReset() {
		seive.reset();
		Assert.assertEquals(1, seive.getItem().intValue());
		Assert.assertEquals(3, seive.getItem().intValue());
		seive.reset();
		Assert.assertEquals(1, seive.getItem().intValue());
	}

}
