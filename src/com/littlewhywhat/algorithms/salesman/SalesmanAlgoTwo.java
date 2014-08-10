package com.littlewhywhat.algorithms.salesman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgoTwo extends
		AbstractAlgorithm<Void, List<City>, Double> {

	
	private class Data {
		private List<City> list;
		private int[] array;
		Data(List<City> list, int[] array) {
			this.list = list;
			this.array = array;
		}
	}
	private List<Data> cache = new ArrayList<Data>();
	private List<Data> current = new ArrayList<Data>();

	@Override
	public void execute() {
		final List<City> cities = getData();
		final City firstCity = cities.remove(0);
		int i = 1;
		for (City city : cities) {
			List<City> set = new ArrayList<City>();
			set.add(city);
			HashMap<Integer, Double> answers = new HashMap<Integer, Double>(
					cities.size());
			answers.put(i++, firstCity.getDistanceTo(city));
			cache.add(new Data(set, new int[getData().size()]));
			//cacheKeys.add(set);
		}
		for (int m = 0; m < cities.size(); m++) {
			long start = System.currentTimeMillis();
			for (Data data : cache) {
				//System.out.println(data.list);
				process(data.list , cities);
				data.list = null;
				data.array = null;
			}
			//System.out.println(currentKeys);
			switchCurrentAndCache();
			long end = System.currentTimeMillis();
			//if (m == 8)
				System.out.println("iteration:" + m + " time:" + (end - start) + " keysSize:" + cache.size());
		}

	}

	private void process(List<City> linkedList, List<City> cities) {
		//int index = linkedList.getLast().getIndex();
		int index = linkedList.get(linkedList.size() - 1).getIndex();
		if (index < cities.size()) {
			ListIterator<City> iterator = cities.listIterator(index);
			while (iterator.hasNext())
				addNewListToCurrent(linkedList, iterator.next());
		}
	}

	private void switchCurrentAndCache() {
		cache = current;
		current = new ArrayList<Data>();
		//cacheKeys = currentKeys;
		//currentKeys = new ArrayList<LinkedList<City>>();
	}

	private void addNewListToCurrent(List<City> linkedList, City newCity) {
		List<City> list = new ArrayList<City>(linkedList);
		list.add(newCity);
		ListIterator<City> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			City city = listIterator.next();
			//listIterator.remove();
			// System.out.println(city);
			//listIterator.add(city);
		}
		current.add(new Data(list, new int[getData().size()]));
		//currentKeys.add(list);
	}

}
