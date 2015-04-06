package com.littlewhywhat.algorithms.sort.merge.test;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.sort.SortingReader;
import com.littlewhywhat.algorithms.sort.merge.EfficientMergeSort;
import com.littlewhywhat.algorithms.sort.merge.SimpleMergeSort;
import com.littlewhywhat.algorithms.sort.quick.FirstQuickSort;
import com.littlewhywhat.algorithms.sort.quick.LastQuickSort;
import com.littlewhywhat.algorithms.sort.quick.MedianOfThreeQuickSort;
import com.littlewhywhat.algorithms.sort.quick.RandomQuickSort;
import com.littlewhywhat.algorithms.sort.test.IntegerArrayGenerator;
import com.littlewhywhat.algorithms.sort.test.SortTest;

public class TestMergeSort {

	private SimpleMergeSort sort;
	private SortingReader reader;
	private EfficientMergeSort sortEff;
	private RandomQuickSort randomQS;
	private FirstQuickSort firstQS;
	private LastQuickSort lastQS;
	private MedianOfThreeQuickSort medianOfThreeQS;
	private IntegerArrayGenerator generator;
	private LinkedList<AbstractAlgorithm<Void, int[], int[]>> algos;
	private int[] answers;
	

	@Before
	public void setUp() throws Exception {
		sort = new SimpleMergeSort();
		sortEff = new EfficientMergeSort();
		randomQS = new RandomQuickSort();
		firstQS = new FirstQuickSort();
		lastQS = new LastQuickSort();
		medianOfThreeQS = new MedianOfThreeQuickSort();
		reader = new SortingReader();
		generator = new IntegerArrayGenerator();
		algos = new LinkedList<AbstractAlgorithm<Void,int[],int[]>>();
	}

	@Test
	public void testExecuteSmall() {
		reader.setInputFilePath(SortTest.INPUT_PATH_SMALL);
		addAllAlgos();
		executeAlgos();
	}

	// @Test
	// public void testExecuteRandom() {
	// 	generator.generate(100000);
	// 	reader.setInputFilePath(SortTest.INPUT_PATH_RANDOM);
	// 	reader.read();
	// 	addAllAlgos();
	// 	executeAlgos();
	// }

	// @Test
	// public void testExecuteBad() {
	// 	generator.generateBad(100000);
	// 	reader.setInputFilePath(SortTest.INPUT_PATH_BAD);
	// 	reader.read();
	// 	algos.push(sort);
	// 	algos.push(sortEff);
	// 	algos.push(randomQS);
	// 	executeAlgos();
	// }

	@Test
	public void testExecute() {
		reader.setInputFilePath(SortTest.INPUT_PATH_BIG);
		reader.read();
		addAllAlgos();
		executeAlgos();
	}

	private void addAllAlgos() {
		algos.push(medianOfThreeQS);
		algos.push(sortEff);
		algos.push(sort);
		algos.push(firstQS);
		algos.push(lastQS);
		algos.push(randomQS);
	}
	
	private void executeAlgos() {
		reader.read();
		sort.setData(reader.getData());
		sort.execute();
		answers = sort.getOutput();
		while (!algos.isEmpty())
			executeAlgo(algos.pop());
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
