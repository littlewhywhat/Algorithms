package com.littlewhywhat.algorithms.optimalbinarytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestOBTAlgo {
	private static final double PRECISION = 0.01;
	private static final double ANSWER = 2.18;
	private final double[] data = new double[] { 0.05, 0.4, 0.08, 0.04, 0.1, 0.1, 0.23 };
	private OBTAlgo algo;
	@Before
	public void setUp() throws Exception {
		algo = new OBTAlgo();
		algo.setData(data);
	}

	@Test
	public void test() {
		algo.execute();
		Assert.assertEquals(ANSWER, algo.getOutput().doubleValue(), PRECISION);
	}

}
