package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class UnmodifiableVerticeList implements List<Vertice> {

	private final List<Vertice> list;

	UnmodifiableVerticeList(List<Vertice> list) {
		this.list = list;
	}

	@Override
	public boolean add(Vertice arg0) {
		return false;
	}

	@Override
	public void add(int arg0, Vertice arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends Vertice> arg0) {
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends Vertice> arg1) {
		return false;
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public Vertice get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<Vertice> iterator() {
		return list.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<Vertice> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<Vertice> listIterator(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0) {
		return false;
	}

	@Override
	public Vertice remove(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public Vertice set(int arg0, Vertice arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<Vertice> subList(int arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}

	List<Vertice> getList() {
		return list;
	}
}
