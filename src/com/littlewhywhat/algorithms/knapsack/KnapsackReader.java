package com.littlewhywhat.algorithms.knapsack;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class KnapsackReader extends TextFileInputReader<Integer, KnapsackItem[]> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		setConfig(scanner.nextInt());
		final KnapsackItem[] items = new KnapsackItem[scanner.nextInt()];
		for (int i = 0; i < items.length; i++)
			items[i] = new SimpleItem(scanner.nextInt(), scanner.nextInt());
		setData(items);
	}

}
