package com.littlewhywhat.algorithms.graphs.clustering;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.littlewhywhat.algorithms.graphs.Connection;
import com.littlewhywhat.algorithms.graphs.SimpleGraph;
import com.littlewhywhat.algorithms.graphs.Vertice;
import com.littlewhywhat.datastructure.Heap;
import com.littlewhywhat.datastructure.SimpleHeap;

public class UnionFindGraph extends SimpleGraph {

	public class WeightedEdge extends SimpleConnection {

		private int weight;
		private SimpleVertice start;
		
		public WeightedEdge(Vertice vertice) {
			super(vertice);
		}

		void addToWeight(int value) {
			this.weight += value;
		}

		void setWeight(int value) {
			this.weight = value;
		}

		public int getWeight() {
			return this.weight;
		}

		@Override
		public String toString() {
			return "Edge [" + getStart() + "," +  getVertice() + "] with weight "
					+ weight;
		}

		public SimpleVertice getStart() {
			return start;
		}

		public void setStart(SimpleVertice start) {
			this.start = start;
		}
	}
	private class PlayerVertice extends SimpleVertice {

		private PlayerVertice leader;
		
		private void setLeader(PlayerVertice vertice) {
			this.leader = vertice;
		}
		
		public PlayerVertice(int index) {
			super(index);
		}

		public PlayerVertice leader() {
			return leader;
		}
		
	}
	public class Group {
		private final List<PlayerVertice> vertices = new LinkedList<PlayerVertice>();
		private Group(PlayerVertice vertice) {
			vertices.add(vertice);
		}
		private List<PlayerVertice> getVertices() {
			return this.vertices;
		}
		
		public PlayerVertice leader() {
			return ((LinkedList<PlayerVertice>) this.vertices).getFirst();
		}
	}
	private Heap<WeightedEdge> heapMin = SimpleHeap.getMinHeap(new Comparator<WeightedEdge>() {

		@Override
		public int compare(WeightedEdge one, WeightedEdge two) {
			Integer oneWeight = one.getWeight();
			Integer twoWeight = two.getWeight();
			return oneWeight.compareTo(twoWeight);
		}});
	
	private Map<PlayerVertice, Group> groups = new HashMap<PlayerVertice, Group>();
	
	public Map<PlayerVertice, Group> getGroups() {
		return this.groups;
	}
	public Heap<WeightedEdge> getMinHeap() {
		return heapMin;
	}
	
	@Override
	protected Connection getNewConnection(Vertice vertice) {
		return new WeightedEdge(vertice);
	}
	
	@Override
	protected Vertice getNewVertice(int index) {
		PlayerVertice vertice = new PlayerVertice(index);
		vertice.setLeader(vertice);
		groups.put(vertice, new Group(vertice));
		return vertice;
	};
	
	public Connection connect(int one, int two, int weight) {
		WeightedEdge connection = (WeightedEdge) super.connect(one, two);
		connection.setWeight(weight);
		connection.setStart((SimpleVertice) get(one));
		heapMin.insert(connection);
		return connection;
	}
	
	public Group find(Vertice vertice) {
		PlayerVertice player = (PlayerVertice) vertice;
		return groups.get(player.leader());
	}
	
	public void union(Group one, Group two) {
		Group larger = one;
		Group smaller = two;
		if (one.getVertices().size() < two.getVertices().size()) {
			larger = two;
			smaller = one;
		}
		List<PlayerVertice> largerV = larger.getVertices();
		List<PlayerVertice> smallerV = smaller.getVertices();
		for (PlayerVertice vertice : smallerV) 
			vertice.setLeader(larger.leader());
		largerV.addAll(smallerV);
		groups.remove(smaller.leader());
	}
	
}
