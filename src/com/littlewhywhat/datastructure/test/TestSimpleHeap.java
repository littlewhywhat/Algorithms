package com.littlewhywhat.datastructure.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.Heap;
import com.littlewhywhat.datastructure.SimpleHeap;

public class TestSimpleHeap {
	
	private final Heap<Integer> heap = new SimpleHeap<Integer>();
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testInsert() {
		heap.insert(4);
		System.out.println(heap);
		heap.insert(0);
		System.out.println(heap);
		heap.insert(3);
		System.out.println(heap);
		heap.insert(-1);
		System.out.println(heap);
	}
	
	@Test
	public void testPoll() {
		heap.insert(4);
		heap.insert(0);
		heap.insert(3);
		heap.insert(-1);
		heap.poll();
		System.out.println(heap);
		heap.poll();
		System.out.println(heap);
		heap.poll();
		System.out.println(heap);
		heap.poll();
		System.out.println(heap);
	}
	
	@Test
	public void testRemove() {
		heap.insert(4);
		heap.insert(0);
		heap.insert(3);
		heap.insert(-1);
		heap.remove(4);
		System.out.println(heap);
		heap.remove(0);
		System.out.println(heap);
		heap.remove(3);
		System.out.println(heap);
		heap.remove(-1);
		System.out.println(heap);
	}
}
