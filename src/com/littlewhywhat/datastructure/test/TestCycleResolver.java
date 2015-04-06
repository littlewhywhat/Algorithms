package com.littlewhywhat.datastructure.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.collection.CycleResolver;

public class TestCycleResolver {

	private static final int LENGTH_OF_CYCLE = 5;
	private CycleResolver resolver;
	
	@Before
	public void setUp() throws Exception {
		resolver = new CycleResolver(LENGTH_OF_CYCLE);
	}

	@Test
	public void testResolveIndex() {
		Assert.assertEquals(3, resolver.resolveIndex(3), 0);
		Assert.assertEquals(0, resolver.resolveIndex(-5), 0);
		Assert.assertEquals(4, resolver.resolveIndex(-1), 0);
		Assert.assertEquals(3, resolver.resolveIndex(-12), 0);
		Assert.assertEquals(1, resolver.resolveIndex(6), 0);		
		Assert.assertEquals(2, resolver.resolveIndex(12), 0);
		
	}

}
