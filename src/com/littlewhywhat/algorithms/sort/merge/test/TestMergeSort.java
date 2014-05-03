package com.littlewhywhat.algorithms.sort.merge.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.sort.SortingReader;
import com.littlewhywhat.algorithms.sort.merge.MergeSort;
import com.littlewhywhat.algorithms.sort.test.SortTest;

public class TestMergeSort {

	private MergeSort sort;
	private SortingReader reader;

	@Before
	public void setUp() throws Exception {
		sort = new MergeSort();
		reader = new SortingReader();
		reader.setInputFilePath(SortTest.INPUT_PATH);
		reader.read();
	}

	@Test
	public void testExecute() {
		sort.setData(reader.getData());
		sort.execute();
	}

}
