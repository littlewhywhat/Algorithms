package com.littlewhywhat.algorithms.sort.merge.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.sort.QuickSort;
import com.littlewhywhat.algorithms.sort.SortingReader;
import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort;
import com.littlewhywhat.algorithms.sort.merge.SimpleMergeSort;
import com.littlewhywhat.algorithms.sort.test.IntegerArrayGenerator;
import com.littlewhywhat.algorithms.sort.test.SortTest;

public class TestMergeSort {

	private SimpleMergeSort sort;
	private SortingReader reader;
	private EfficientMergeSort sortEff;
	private QuickSort quickSort;
	private IntegerArrayGenerator generator;
	private int[] answers;

	@Before
	public void setUp() throws Exception {
		sort = new SimpleMergeSort();
		sortEff = new EfficientMergeSort();
		quickSort = new QuickSort();
		reader = new SortingReader();
		generator = new IntegerArrayGenerator();
	}

	@Test
	public void testExecuteSmall() {
		reader.setInputFilePath(SortTest.INPUT_PATH_SMALL);
		executeAlgos();
	}

	@Test
	public void testExecuteRandom() {
		generator.generate(100000);
		reader.setInputFilePath(SortTest.INPUT_PATH_RANDOM);
		executeAlgos();
	}

	@Test
	public void testExecuteBad() {
		generator.generateBad(100000);
		reader.setInputFilePath(SortTest.INPUT_PATH_BAD);
		executeAlgos();
	}

	@Test
	public void testExecute() {
		reader.setInputFilePath(SortTest.INPUT_PATH_BIG);
		reader.read();
		executeAlgos();
	}

	private void executeAlgos() {
		reader.read();
		sort.setData(reader.getData());
		sort.execute();
		answers = sort.getOutput();
		executeAlgo(sort);
		executeAlgo(sortEff);
		executeAlgo(quickSort);
		System.out.println();
		
	}

	private void executeAlgo(AbstractAlgorithm<Void, int[], int[]> algo) {
		reader.read();
		algo.setData(reader.getData());
		long start = System.currentTimeMillis();
		algo.execute();
		long end = System.currentTimeMillis();
		Assert.assertArrayEquals(answers, algo.getOutput());
		System.out.print(algo.getClass().getSimpleName() + " " + (end - start) + " ");
	}
}
