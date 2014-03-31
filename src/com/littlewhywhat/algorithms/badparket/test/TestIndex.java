package com.littlewhywhat.algorithms.badparket.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.badparket.Data;
import com.littlewhywhat.algorithms.badparket.Data.Index;

public class TestIndex {
	private Data data;
	private boolean[][] isBad;
	private static final int xLength = 2;
	private static final int yLength = 3;

	@Before
	public void setUp() throws Exception {
		this.isBad = TestBadParket.isBad;
	}

	private void recoverData() {
		this.data = TestBadParket.getReaderData();
	}

	@Test
	public void testIndexNeighbours() {
		recoverData();
		for (int x = 0; x < xLength; x++)
			for (int y = 0; y < yLength; y++)
				checkIndexNeighbours(x, y, this.data.pop());
		Assert.assertEquals(false, this.data.hasUnProcessed());
	}

	private void checkIndexNeighbours(int x, int y, Index pop) {
		Assert.assertEquals(x, pop.getX());
		Assert.assertEquals(y, pop.getY());

		if (x > 0) {
			Assert.assertEquals(true, pop.hasUpNeighbour());
			Index upNeighbour = pop.getUpNeighbour();
			TestData.checkIndex(x - 1, y, isBad[x - 1][y], true, upNeighbour);
		}
		if (x < xLength - 1) {
			Assert.assertEquals(true, pop.hasDownNeighbour());
			Index downNeighbour = pop.getDownNeighbour();
			TestData.checkIndex(x + 1, y, isBad[x + 1][y], false, downNeighbour);
		}
		if (y > 0) {
			Assert.assertEquals(true, pop.hasLeftNeighbour());
			Index leftNeighbour = pop.getLeftNeighbour();
			TestData.checkIndex(x, y - 1, isBad[x][y - 1], true, leftNeighbour);
		}
		if (y < yLength - 1) {
			Assert.assertEquals(true, pop.hasRightNeighbour());
			Index rightNeighbour = pop.getRightNeighbour();
			TestData.checkIndex(x, y + 1, isBad[x][y + 1], false,
					rightNeighbour);
		}

	}

	@Test
	public void testIsProcessed() {
		recoverData();
		for (int i = 0; i < yLength; i++) {
			Index downNeighbour = this.data.pop().getDownNeighbour();
			TestData.checkIndex(1, i, isBad[1][i], false, downNeighbour);
			downNeighbour.setIsProcessed();
		}
		Assert.assertEquals(false, this.data.hasUnProcessed());
	}

}
