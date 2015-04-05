package com.littlewhywhat.algorithms.sort.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.sort.InsertionSort;
import com.littlewhywhat.algorithms.sort.SortingReader;

public class TestInsertionSort {
	private SortingReader reader;
	private InsertionSort sort;

	@Before
	public void setUp() throws Exception {
		reader = new SortingReader();
		sort = new InsertionSort();
	}

	@Test
	public void testExecute() {
		reader.setInputFilePath(SortTest.INPUT_PATH_BIG);
		reader.read();
		sort.setData(reader.getData());
		sort.setConfig(new int[] { 0, reader.getData().length });
		sort.execute();
		System.out.println();
	}

}
