package com.littlewhywhat.datastructure.test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.Heap;
import com.littlewhywhat.datastructure.SimpleHeap;

public class TestSimpleHeap {
	
	private Heap<Integer> minHeap;
	private Heap<Integer> maxHeap;
	private LinkedList<Integer> minInput = new LinkedList<Integer>();
	private LinkedList<Integer> maxInput = new LinkedList<Integer>();
	private final Random random = new Random();
	
	private Comparator<Integer> comparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}};
	private final int[] MIN_HEAP_ANSWERS = new int[] {-1, 0, 3, 4};
	private final int[] MAX_HEAP_ANSWERS = new int[] {4, 3, 0, -1};
		
		
		
	@Before
	public void setUp() {
		minHeap = SimpleHeap.getMinHeap(comparator);
		maxHeap = SimpleHeap.getMaxHeap(comparator);
		fillInput(minInput);
		fillInput(maxInput);
		randomInsert(minInput, minHeap);
		randomInsert(maxInput, maxHeap);
	}
	
	private void fillInput(LinkedList<Integer> input) {
		for(int item : MIN_HEAP_ANSWERS)
			input.add(item);
	}

	private void randomInsert(LinkedList<Integer> input, Heap<Integer> heap) {
		while (!input.isEmpty()) {
			Integer item = input.get(random.nextInt(input.size()));
			input.remove(item);
			heap.insert(item);
		}
	}
	
	@Test
	public void testPoll() {
		testPoll(minHeap, MIN_HEAP_ANSWERS);
		testPoll(maxHeap, MAX_HEAP_ANSWERS);
	}
	
	private void testPoll(Heap<Integer> heap, int[] answers) {
		for (Integer answer : answers)
			Assert.assertEquals(answer, heap.poll());
	}

	@Test
	public void testRemove() {
		minHeap.remove(4);
		System.out.println(minHeap);
		minHeap.remove(0);
		System.out.println(minHeap);
		minHeap.remove(3);
		System.out.println(minHeap);
		minHeap.remove(-1);
		System.out.println(minHeap);
		
		maxHeap.remove(0);
		System.out.println(maxHeap);
		maxHeap.remove(3);
		System.out.println(maxHeap);
		maxHeap.remove(4);
		System.out.println(maxHeap);
		maxHeap.remove(-1);
	}
}
