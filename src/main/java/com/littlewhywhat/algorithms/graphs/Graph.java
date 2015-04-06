<<<<<<< HEAD:src/com/littlewhywhat/algorithms/graphs/Graph.java
package com.littlewhywhat.algorithms.graphs;

import java.util.Collection;
import java.util.List;

public interface Graph<I, T extends Id<I>, E extends Edge<I,T>> extends Collection<T> {
	E connect(T one, T two);
	T get(I id);
	List<E> getIn(T item);
	List<E> getOut(T item);
	List<E> edges();
}
=======
package com.littlewhywhat.algorithms.graphs;

import java.util.Map;

public interface Graph extends Map<Integer, Vertice>, Iterable<Vertice> {
	Connection connect(int one, int two);
	UnmodifiableList<Connection> getConnections(Vertice vertice);
}
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/main/java/com/littlewhywhat/algorithms/graphs/Graph.java
