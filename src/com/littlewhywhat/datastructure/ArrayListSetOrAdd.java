package com.littlewhywhat.datastructure;

import java.util.ArrayList;

public class ArrayListSetOrAdd<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void setOrGet(int index, E element) {
		if (this.size() > index)
			set(index, element);
		else 
			add(index, element);
	}
}
