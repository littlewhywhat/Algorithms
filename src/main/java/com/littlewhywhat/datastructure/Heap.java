package com.littlewhywhat.datastructure;

import java.util.Comparator;

public interface Heap<T> {
	void setComparator(Comparator<T> comparator);
	void insert(T item);
	void remove(T item);
	T poll();
	T peek();
	int size();
<<<<<<< HEAD:src/com/littlewhywhat/datastructure/Heap.java
=======
	void clear();
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/main/java/com/littlewhywhat/datastructure/Heap.java
}
