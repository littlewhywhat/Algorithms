package com.littlewhywhat.algorithms.knapsack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class KnapsackReader extends TextFileInputReader<Integer, List<KnapsackItem>> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		setConfig(scanner.nextInt());
		final List<KnapsackItem> items = new ArrayList<KnapsackItem>(scanner.nextInt());
		while(scanner.hasNext())
			items.add(new SimpleItem(scanner.nextInt(), scanner.nextInt()));
		setData(items);
	}

}
