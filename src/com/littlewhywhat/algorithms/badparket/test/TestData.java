package com.littlewhywhat.algorithms.badparket.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.badparket.Data;
import com.littlewhywhat.algorithms.badparket.Data.Index;

public class TestData {

	private Data data;
	private boolean[][] isBad;

	@Before
	public void setUp() throws Exception {
		isBad = TestBadParket.isBad;
	}

	private void recoverData() {
		this.data = TestBadParket.getReaderData();
	}

	@Test
	public void testPop() {
		recoverData();
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 3; j++)
				checkIndex(i, j, isBad[i][j] ,true, this.data.pop());
		checkDataHasUnProcessed(false);
	}

	public static void checkIndex(int x, int y, boolean isBad, boolean isProcessed, Index index) {
		Assert.assertEquals(x, index.getX());
		Assert.assertEquals(y, index.getY());
		Assert.assertEquals(isProcessed, index.isProcessed());
		Assert.assertEquals(isBad, index.isBad());
	}

	private void checkDataHasUnProcessed(boolean expected) {
		Assert.assertEquals(expected, this.data.hasUnProcessed());
	}

}
