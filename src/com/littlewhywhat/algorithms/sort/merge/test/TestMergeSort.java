package com.littlewhywhat.algorithms.sort.merge.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.sort.SortingReader;
import com.littlewhywhat.algorithms.sort.merge.SimpleMergeSort;
import com.littlewhywhat.algorithms.sort.test.SortTest;

public class TestMergeSort {

	private SimpleMergeSort sort;
	private SortingReader reader;

	@Before
	public void setUp() throws Exception {
		sort = new SimpleMergeSort();
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
