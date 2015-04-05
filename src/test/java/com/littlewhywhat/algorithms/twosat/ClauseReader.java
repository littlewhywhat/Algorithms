package com.littlewhywhat.algorithms.twosat;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class ClauseReader extends TextFileInputReader<Integer, List<SimpleClause>> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		setConfig(scanner.nextInt());
		final List<SimpleClause> data = new ArrayList<SimpleClause>();
		while (scanner.hasNext()) {
			int one = scanner.nextInt();
			int two = scanner.nextInt();
			data.add(SimpleClause.getNewSimpleClause(one < 0, Math.abs(one) - 1, two < 0, Math.abs(two) - 1));
		}
		setData(data);
	}
	
}
