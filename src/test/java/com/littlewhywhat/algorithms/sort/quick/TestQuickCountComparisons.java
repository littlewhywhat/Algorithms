package com.littlewhywhat.algorithms.sort.quick;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.sort.SortingReader;
import com.littlewhywhat.algorithms.sort.test.SortTest;

public class TestQuickCountComparisons {

	private static final String INPUT_FILE_PATH = SortTest.FOLDER + "QuickCountComparisons.txt";
	private FirstQuickSort first;
	private SortingReader reader;
	private LastQuickSort last;
	private MedianOfThreeQuickSort medianOfThree;
	
	@Before
	public void setUp() throws Exception {
		first = new FirstQuickSort();
		last = new LastQuickSort();
		medianOfThree = new MedianOfThreeQuickSort();
		reader = new SortingReader();
		reader.setInputFilePath(INPUT_FILE_PATH);
	}

	@Test
	public void test() {
		executeQuickSort(first);
		executeQuickSort(last);
		executeQuickSort(medianOfThree);
	}

	private void executeQuickSort(AbstractQuickSort quickSort) {
		reader.read();
		quickSort.setData(reader.getData());
		quickSort.execute();
		System.out.println(quickSort.getClass().getSimpleName() + " " + quickSort.getNumberOfComparisons());
	}

}
