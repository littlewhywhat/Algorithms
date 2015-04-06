package com.littlewhywhat.datastructure.sieve;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSieveDosator {
	SieveDosator<Integer> dosator;
	SerialArraySieve<Integer> sieve;
	Integer[] dose = new Integer[2];
	private static final Integer[] ARRAY = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 };
	
	@Before
	public void setUp() throws Exception {
		dosator = new SieveDosator<Integer>();
		sieve = new SerialArraySieve<Integer>();
		dosator.setArraySieve(sieve);
		dosator.setArray(ARRAY);	
		dosator.setDose(dose);
	}

	@Test
	public void testNextDose() {
		dosator.nextDose();
		Assert.assertEquals(1, dose[0], 0);
		Assert.assertEquals(5, dose[1], 0);
		dosator.nextDose();
		Assert.assertEquals(2, dose[0], 0);
		Assert.assertEquals(6, dose[1], 0);
	}

	@Test
	public void testHasDose() {
		while (dosator.hasDose()) {
			dosator.nextDose();
		}
		Assert.assertEquals(4, dose[0], 0);
		Assert.assertEquals(8, dose[1], 0);
	}
	
	@Test
	public void testReset() {
		while (dosator.hasDose()) {
			dosator.nextDose();
		}
		dosator.reset();
		dosator.nextDose();
		Assert.assertEquals(1, dose[0], 0);
		Assert.assertEquals(5, dose[1], 0);
	}

}
