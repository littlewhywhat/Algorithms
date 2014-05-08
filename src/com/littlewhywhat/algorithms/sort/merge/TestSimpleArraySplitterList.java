package com.littlewhywhat.algorithms.sort.merge;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort.ArraySplitterList;
import com.littlewhywhat.algorithms.sort.merge.SimpleArraySplitterList.SimpleSplitter;

public class TestSimpleArraySplitterList {

	private ArraySplitterList splitterList;
	private int[] array = new int[] { 1, 2, 3, 4, 5 };

	@Before
	public void setUp() throws Exception {
		splitterList = new SimpleArraySplitterList();
		splitterList.setArray(array);
	}

	@Test
	public void testSizeCache() {
		Assert.assertEquals(1, splitterList.sizeCache());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, splitterList.size());
		splitterList.addFirst(0);
		Assert.assertEquals(1, splitterList.size());
		splitterList.addLast(0);
		Assert.assertEquals(2, splitterList.size());
	}

	@Test
	public void testAddFirst() {
		for (int i = 0; i < 5; i++) {
			splitterList.addFirst(i);
			Assert.assertEquals(i, splitterList.getFirst().getIndex());
		}
	}

	@Test
	public void testAddLast() {
		for (int i = 0; i < 5; i++) {
			splitterList.addLast(i);
			Assert.assertEquals(i, splitterList.getLast().getIndex());
		}
	}

	@Test
	public void testGetFirst() {
		Assert.assertEquals(null, splitterList.getFirst());
		
		for (int i = 0; i < 5; i++)
			splitterList.addLast(i);
		Assert.assertEquals(0, splitterList.getFirst().getIndex());
	}

	@Test
	public void testGetLast() {
		Assert.assertEquals(null, splitterList.getLast());
		for (int i = 0; i < 5; i++)
			splitterList.addFirst(i);
		Assert.assertEquals(0, splitterList.getLast().getIndex());
	}

	@Test
	public void testGet() {
		for (int i = 0; i < 5; i++)
			splitterList.addLast(i);
		for (int i = 0; i < 5; i++)
			Assert.assertEquals(i, splitterList.get(i).getIndex());
		Assert.assertEquals(null, splitterList.get(-1));
		Assert.assertEquals(null, splitterList.get(5));
	}

	@Test
	public void testRemove() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
			splitterList.addLast(i);
		}
		Assert.assertEquals(list.size(), splitterList.size());
		Random random = new Random();
		int randomIndex = random.nextInt(10);
		list.remove(randomIndex);
		splitterList.remove(randomIndex);
		int count = 0;
		for (Integer index : list) {
			Assert.assertEquals(index.intValue(), splitterList.get(count)
					.getIndex());
			count++;
		}
		Assert.assertEquals(list.size(), splitterList.size());

	}

	@Test
	public void testToString() {
		for (int i = 0; i < 2; i++)
			splitterList.addLast(i);
		String string = "[[itemId=" + 0 + ", id="+1+"],[itemId=" + 1 + ", id="+ 2 +"]]";
		Assert.assertEquals(string, splitterList.toString());
	}

	@Test
	public void testGetItem() {
		splitterList.addFirst(2);
		Assert.assertEquals(array[2], splitterList.getFirst().getItem());
	}
	
	@Test
	public void testGetLength() {
		splitterList.addFirst(2);
		Assert.assertEquals(3, ((SimpleSplitter) splitterList.getFirst()).getLength());
		splitterList.addLast(3);
		Assert.assertEquals(1, ((SimpleSplitter) splitterList.getFirst()).getLength());
		Assert.assertEquals(2, ((SimpleSplitter) splitterList.getLast()).getLength());
	}

	@Test
	public void testSwapItem() {
		splitterList.addFirst(2);
		int oldItem = array[2];
		Assert.assertEquals(oldItem, splitterList.getFirst().swapItem(10));
		Assert.assertEquals(10, splitterList.getFirst().getItem());
		Assert.assertEquals(10, array[2]);
	}
	
	@Test
	public void testMove() {
		splitterList.addFirst(0);
		for (int i = 0; i < array.length; i++) {
			Assert.assertEquals(array[i], splitterList.getFirst().getItem());
			splitterList.getFirst().move();
		}
	}

	
	@Test
	public void testClean() {
		for (int i = 0; i < 5; i++)
			splitterList.addLast(i);
		for (int i = 0; i < 5; i++)
			Assert.assertEquals(1, ((SimpleSplitter) splitterList.get(i)).getLength());
		for (int i = 5; i > 0; i--) {
			Assert.assertEquals(i, splitterList.size());
			splitterList.getFirst().move();
			splitterList.clean();
		}
		Assert.assertEquals(0, splitterList.size());
		
	}
	
	@Test
	public void testGetNextSplitter() {
		splitterList.addFirst(1);
		Assert.assertEquals(null ,splitterList.getFirst().getNextSplitter());
		splitterList.addLast(2);
		Assert.assertEquals(splitterList.getLast(), splitterList.getFirst().getNextSplitter());
	}
	
	@Test
	public void testGetPrevSplitter() {
		splitterList.addFirst(1);
		Assert.assertEquals(null ,splitterList.getFirst().getPrevSplitter());
		splitterList.addFirst(2);
		Assert.assertEquals(splitterList.getFirst(), splitterList.getLast().getPrevSplitter());
	}
}
