package com.littlewhywhat.algorithms.sort.test;

import java.util.LinkedList;
import java.util.Random;

import com.littlewhywhat.algorithms.io.txt.StringArrayOutputWriter;

public class IntegerArrayGenerator {
	
	private StringArrayOutputWriter writer = new StringArrayOutputWriter();
	private int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
			13, 14, 15, 16 };
	private Random random = new Random();
	
	public void generate() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int item : array)
			list.add(item);
		String[] output = new String[array.length];
		for (int i = 0; i < output.length; i++) {
			int index = random.nextInt(list.size());
			output[i] = String.valueOf(list.remove(index));
		}
		writer.setOutput(output);
		writer.setOutputFilePath(SortTest.INPUT_PATH_RANDOM);
		writer.write();
	}
}
