package com.littlewhywhat.algorithms.salesman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.datastructure.Heap;
import com.littlewhywhat.datastructure.SimpleHeap;

public class SalesmanAlgoTwo extends
		AbstractAlgorithm<Void, List<City>, Double> {

	
	private LinkedHashMap<List<City>, int[]> cache = new LinkedHashMap<List<City>, int[]> ();
	private LinkedHashMap<List<City>, int[]> current = new LinkedHashMap<List<City>, int[]>();
	
	private Heap<Integer> heapMin = SimpleHeap.getMinHeap(new Comparator<Integer>(){

		@Override
		public int compare(Integer one, Integer two) {
			return one.compareTo(two);
		}
		
	});
	
	
	@Override
	public void execute() {
		final List<City> cities = getData();
		final City firstCity = cities.remove(0);
		int i = 1;
		for (City city : cities) {
			List<City> set = new ArrayList<City>();
			set.add(city);
			int[] answers = new int[cities.size() + 1];
			//answers[i++] = firstCity.getDistanceTo(city);
			cache.put(set, answers);
			//cacheKeys.add(set);
		}
		for (int m = 0; m < cities.size(); m++) {
			//System.out.println(cache);
			long start = System.currentTimeMillis();
			Iterator<List<City>> iterator = cache.keySet().iterator();
			while (iterator.hasNext()) {
				//System.out.println(data.list);
				process(iterator.next() , cities);
				iterator.remove();
			}
			
			switchCurrentAndCache();
			long end = System.currentTimeMillis();
			//if (m == cities.size()-2)
				System.out.println("iteration:" + m + " time:" + (end - start) + " keysSize:" + cache.size());
		}
		System.out.println();
	}

	private void process(List<City> linkedList, List<City> cities) {
		//int index = ((LinkedList<City>) linkedList).getLast().getIndex();
		int index = linkedList.get(linkedList.size() - 1).getIndex();
		if (index < cities.size()) {
			ListIterator<City> iterator = cities.listIterator(index);
			while (iterator.hasNext())
				addNewListToCurrent(linkedList, iterator.next());
		}
	}

	private void switchCurrentAndCache() {
		cache = current;
		current = new LinkedHashMap<List<City>, int[]>();
		//cacheKeys = currentKeys;
		//currentKeys = new ArrayList<LinkedList<City>>();
	}

	private void addNewListToCurrent(List<City> linkedList, City newCity) {
		List<City> list = new ArrayList<City>(linkedList);
		int[] hashMap = new int[getData().size() + 1];
		list.add(newCity);
		
		ListIterator<City> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			City city = listIterator.next();
			//listIterator.remove();
			//Iterator<City> iterator = list.iterator();
			//while (iterator.hasNext()) {
			//	City k = iterator.next();
			//	heapMin.insert(cache.get(list)[k.getIndex()] + k.getDistanceTo(city));
			//}
			//listIterator.add(city);
			//hashMap[city.getIndex()] = heapMin.peek();
			//((SimpleHeap<Integer>) heapMin).clear();
		}
		current.put(list, hashMap );
		//currentKeys.add(list);
	}

}
