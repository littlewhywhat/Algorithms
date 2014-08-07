package com.littlewhywhat.algorithms.salesman;

import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgo extends
		AbstractAlgorithm<Void, List<City>, Double> {

	private class CityNode extends SimpleNode {
		private final City city;
		private CityNode(City city) {
			this.city = city;
		}
		private City getCity() {
			return this.city;
		}
	}
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
