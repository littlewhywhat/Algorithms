package com.littlewhywhat.algorithms.sort.merge.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.sort.SortingReader;
import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort;
import com.littlewhywhat.algorithms.sort.merge.SimpleMergeSort;
import com.littlewhywhat.algorithms.sort.test.IntegerArrayGenerator;
import com.littlewhywhat.algorithms.sort.test.SortTest;

public class TestMergeSort {

	private SimpleMergeSort sort;
	private SortingReader reader;
	private EfficientMergeSort sortEff;
	private IntegerArrayGenerator generator;
	
	@Before
	public void setUp() throws Exception {
		sort = new SimpleMergeSort();
		sortEff = new EfficientMergeSort();
		reader = new SortingReader();
		generator = new IntegerArrayGenerator();
	}

	@Test
	public void testExecuteSmall() {
		reader.setInputFilePath(SortTest.INPUT_PATH_SMALL);
		reader.read();
		executeAlgos();
	}
	
	@Test
	public void testExecuteRandom() {
		generator.generate();
		reader.setInputFilePath(SortTest.INPUT_PATH_RANDOM);
		reader.read();
		executeAlgos();
	}
	
	@Test
	public void testExecute() {
		reader.setInputFilePath(SortTest.INPUT_PATH_BIG);
		reader.read();
		executeAlgos();
	}

	private void executeAlgos() {
		sort.setData(reader.getData());
		sort.execute();
		sortEff.setData(reader.getData());
		sortEff.execute();
		System.out.println();
	}

}
