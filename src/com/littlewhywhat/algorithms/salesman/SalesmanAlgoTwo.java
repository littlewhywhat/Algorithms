package com.littlewhywhat.algorithms.salesman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgoTwo extends
		AbstractAlgorithm<Void, List<City>, Double> {

	private LinkedHashMap<LinkedList<City>, HashMap<Integer, Double>> cache = new LinkedHashMap<LinkedList<City>, HashMap<Integer, Double>>();
	private LinkedHashMap<LinkedList<City>, HashMap<Integer, Double>> current = new LinkedHashMap<LinkedList<City>, HashMap<Integer, Double>>();
	private List<LinkedList<City>> cacheKeys = new ArrayList<LinkedList<City>>();
	private List<LinkedList<City>> currentKeys = new ArrayList<LinkedList<City>>();

	@Override
	public void execute() {
		final List<City> cities = getData();
		final City firstCity = cities.remove(0);
		int i = 1;
		for (City city : cities) {
			LinkedList<City> set = new LinkedList<City>();
			set.add(city);
			HashMap<Integer, Double> answers = new HashMap<Integer, Double>(
					cities.size());
			answers.put(i++, firstCity.getDistanceTo(city));
			cache.put(set, answers);
			//cacheKeys.add(set);
		}
		for (int m = 0; m < cities.size(); m++) {
			long start = System.currentTimeMillis();
			for (LinkedList<City> linkedList : cache.keySet()) {
				//System.out.println(linkedList);
				process(linkedList , cities);
			}
			//System.out.println(currentKeys);
			switchCurrentAndCache();
			long end = System.currentTimeMillis();
			//if (m == 8)
				System.out.println("iteration:" + m + " time:" + (end - start) + " keysSize:" + cacheKeys.size());
		}

	}

	private void process(LinkedList<City> linkedList, List<City> cities) {
		int index = linkedList.getLast().getIndex();
		if (index < cities.size()) {
			ListIterator<City> iterator = cities.listIterator(index);
			while (iterator.hasNext())
				addNewListToCurrent(linkedList, iterator.next());
		}
	}

	private void switchCurrentAndCache() {
		cache = current;
		current = new LinkedHashMap<LinkedList<City>, HashMap<Integer, Double>>();
		//cacheKeys = currentKeys;
		//currentKeys = new ArrayList<LinkedList<City>>();
	}

	private void addNewListToCurrent(LinkedList<City> linkedList, City newCity) {
		LinkedList<City> list = new LinkedList<City>(linkedList);
		list.addLast(newCity);
		ListIterator<City> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			City city = listIterator.next();
			listIterator.remove();
			// System.out.println(city);
			listIterator.add(city);
		}
		current.put(list, new HashMap<Integer, Double>());
		//currentKeys.add(list);
	}

}
