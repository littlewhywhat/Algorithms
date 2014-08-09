package com.littlewhywhat.algorithms.salesman;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgoTwo extends
		AbstractAlgorithm<Void, List<City>, Double> {

	private Map<HashSet<City>, double[]> map = new HashMap<HashSet<City>, double[]>();
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		final List<City> cities = getData();
		final City firstCity = cities.remove(0);
		for (City city: cities ) {
			HashSet<City> set = new HashSet<City>();
			set.add(firstCity);
			set.add(city);
			map.put(set, new double[cities.size()]);
		}
		HashSet<City> set = new HashSet<City>();
		set.add(firstCity);
		set.add(cities.get(0));
		set.add(cities.get(1));
		set.remove(cities.get(1));
		System.out.println(map.get(set));
	}

}
