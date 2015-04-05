package com.littlewhywhat.algorithms.sort;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class SortingReader extends TextFileInputReader<Void, int[]> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		List<Integer> arrayList = new ArrayList<Integer>();
		while (scanner.hasNext())
			arrayList.add(scanner.nextInt());
		int[] data = new int[arrayList.size()];
		for (int i = 0; i < data.length; i++)
			data[i] = arrayList.get(i);
		setData(data);
	}

}
