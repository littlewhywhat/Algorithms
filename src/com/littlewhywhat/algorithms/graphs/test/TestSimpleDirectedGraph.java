package com.littlewhywhat.algorithms.graphs.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.graphs.Id;
import com.littlewhywhat.algorithms.graphs.SimpleDirectedGraph;

public class TestSimpleDirectedGraph {

	private class Item implements Id{

		private int id;
		
		private Item(int id) {
			this.id = id;
		}
		
		@Override
		public int getId() {
			return id;
		}

		@Override
		public String toString() {
			return String.valueOf(id);
		}
		
		
	}
	
	private final Graph<Item> graph = new SimpleDirectedGraph<Item>();
	
	@Before
	public void setUp() throws Exception {
		graph.add(new Item(1));
		
	}

	@Test
	public void test() {
		graph.add(new Item(1));
	}

}
