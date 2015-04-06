package com.littlewhywhat.algorithms.schedule;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import com.littlewhywhat.datastructure.SimpleHeap;

public class SimpleSchedule implements Schedule<SimpleJob> {

	private final SimpleHeap<SimpleJob> heap;
	private int time;
	
	public SimpleSchedule(Comparator<SimpleJob> comparator) {
		heap = SimpleHeap.getMaxHeap(comparator);
	}

	@Override
	public boolean add(SimpleJob item) {
		heap.insert(item);
		return true;
	}

	@Override
	public SimpleJob element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(SimpleJob e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SimpleJob peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleJob poll() {
		SimpleJob job = heap.poll();
		time += job.getLength();
		return job;
	}

	@Override
	public SimpleJob remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(Collection<? extends SimpleJob> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return heap.size() == 0;
	}

	@Override
	public Iterator<SimpleJob> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public int resetTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
