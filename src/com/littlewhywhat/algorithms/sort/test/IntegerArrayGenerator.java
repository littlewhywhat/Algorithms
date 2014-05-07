package com.littlewhywhat.algorithms.sort.test;

import java.util.Random;

import com.littlewhywhat.algorithms.io.txt.StringArrayOutputWriter;

public class IntegerArrayGenerator {
	
	private StringArrayOutputWriter writer = new StringArrayOutputWriter();
	private Random random = new Random();
	
	public void generate(int size) {
		String[] output = new String[size];
		for (int i = 0; i < output.length; i++) {
			int index = random.nextInt(size);
			output[i] = String.valueOf(index);
		}
		writer.setOutput(output);
		writer.setOutputFilePath(SortTest.INPUT_PATH_RANDOM);
		writer.write();
	}
	
	public void generateBad(int size) {
		String[] output = new String[size];
		int first = 2;

		for (int i = 0; i < size/2; i++) {
			output[i] = String.valueOf(first);
			first += 3;
		}
		first = 0;
		for (int i = size/2 + 1; i < size; i = i + 2) {
			output[i-1] = String.valueOf(first);
			output[i] = String.valueOf(first+1);
			first += 3;
		}
		if (output[size - 1 ] == null)
			output[size -1] = String.valueOf(first);
		writer.setOutput(output);
		writer.setOutputFilePath(SortTest.INPUT_PATH_BAD);
		writer.write();
	}
}
