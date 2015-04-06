package com.littlewhywhat.algorithms.salesman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class SalesmanAlgoTwo extends
		AbstractAlgorithm<Void, List<City>, Double> {

	
	private LinkedHashMap<List<City>, int[]> cache = new LinkedHashMap<List<City>, int[]> ();
	private LinkedHashMap<List<City>, int[]> current = new LinkedHashMap<List<City>, int[]>();
	private int[][] distances;
	
	
	@Override
	public void execute() {
		final List<City> cities = getData();
		setDistances(cities);
		final City firstCity = cities.remove(0);
		int i = 1;
		for (City city : cities) {
			List<City> set = new ArrayList<City>();
			set.add(city);
			int[] answers = new int[cities.size() + 1];
			answers[i++] = distances[city.getIndex()][firstCity.getIndex()];
			cache.put(set, answers);
		}
		for (int m = 0; m < cities.size() - 1; m++) {
			long start = System.currentTimeMillis();
			Iterator<List<City>> iterator = cache.keySet().iterator();
			while (iterator.hasNext()) {
				process(iterator.next() , cities);
				iterator.remove();
			}
			switchCurrentAndCache();
			long end = System.currentTimeMillis();
			System.out.println("iteration:" + m + " time:" + (end - start) + " keysSize:" + cache.size());
		}
		Iterator<List<City>> iterator = cache.keySet().iterator();
		List<City> list = iterator.next();
		int[] results = cache.get(list);
		int min = Integer.MAX_VALUE;
		for (City city : list) {
			int temp = results[city.getIndex()] + distances[city.getIndex()][firstCity.getIndex()];
			if (temp < min)
				min = temp;
		}
		setOutput(((double)min)/100);
		System.out.println(getOutput());
	}

	private void setDistances(List<City> cities) {
		distances = new int[cities.size()][cities.size()];
		for (City city : cities) {
			for (City cityOther : cities) {
				distances[city.getIndex()][cityOther.getIndex()] = (int) (city.getDistanceTo(cityOther) * 100);
			}
		}
	}

	private void process(List<City> list, List<City> cities) {
		int index = list.get(list.size() - 1).getIndex();
		if (index < cities.size()) {
			ListIterator<City> iterator = cities.listIterator(index);
			while (iterator.hasNext())
				addNewListToCurrent(list, iterator.next());
		}
	}

	private void switchCurrentAndCache() {
		cache = current;
		current = new LinkedHashMap<List<City>, int[]>();
	}

	private void addNewListToCurrent(List<City> linkedList, City newCity) {
		List<City> list = new ArrayList<City>(linkedList);
		int[] hashMap = new int[getData().size() + 1];
		list.add(newCity);
		
		ListIterator<City> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			City city = listIterator.next();
			listIterator.remove();
			Iterator<City> iterator = list.iterator();
			int min = Integer.MAX_VALUE;
			while (iterator.hasNext()) {
				City k = iterator.next();
				int temp = cache.get(list)[k.getIndex()] + distances[k.getIndex()][city.getIndex()];
				if (temp < min)
					min = temp;
			}
			listIterator.add(city);
			hashMap[city.getIndex()] = min;
		}
		current.put(list, hashMap );
	}

}
