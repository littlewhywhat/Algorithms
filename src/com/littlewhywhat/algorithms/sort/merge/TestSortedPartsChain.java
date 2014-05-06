package com.littlewhywhat.algorithms.sort.merge;


import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.sort.merge.SortedPartsChain.SortedPart;



public class TestSortedPartsChain {

	private static final String EMPTY_MESSAGE = "SortedPartsChain is empty";
	private SortedPartsChain chain;
	
	@Before
	public void setUp() throws Exception {
		chain = new SortedPartsChain();
	}

	@Test
	public void testAddGetLast() {
		SortedPart part = chain.getNewSortedPart();
		chain.addLast(chain.getNewSortedPart());
		chain.addLast(part);		
		Assert.assertEquals(part, chain.getLast());
		part.remove();
		part = chain.getLast();
		part.remove();
		String message = null;
		try {
			chain.getLast();
		}
		catch (NoSuchElementException e) {
			message = e.getMessage();
		}
		Assert.assertEquals(message, EMPTY_MESSAGE);
	}

	@Test
	public void testAddGetFirst() {
		SortedPart part = chain.getNewSortedPart();
		chain.addFirst(chain.getNewSortedPart());
		chain.addFirst(part);		
		Assert.assertEquals(part, chain.getFirst());
		part.remove();
		part = chain.getFirst();
		part.remove();
		String message = null;
		try {
			chain.getFirst();
		}
		catch (NoSuchElementException e) {
			message = e.getMessage();
		}
		Assert.assertEquals(message, EMPTY_MESSAGE);
	}

	@Test
	public void testSize() {
		chain.addFirst(chain.getNewSortedPart());
		Assert.assertEquals(1, chain.size());
		chain.addLast(chain.getNewSortedPart());
		Assert.assertEquals(2, chain.size());
		while (chain.size() != 0)
			chain.getFirst().remove();
	}

	@Test
	public void testOneRemained() {
		chain.addFirst(chain.getNewSortedPart());
		Assert.assertEquals(true, chain.oneRemained());
		chain.addFirst(chain.getNewSortedPart());
		Assert.assertEquals(false, chain.oneRemained());
		chain.getFirst().remove();
		chain.getFirst().remove();
		Assert.assertEquals(false, chain.oneRemained());
	}

}
