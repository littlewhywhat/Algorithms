package com.littlewhywhat.algorithms.twosum;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class TwoSumReader extends TextFileInputReader<Void, List<Long>> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		setData(new ArrayList<Long>());
		while (scanner.hasNextLong())
			getData().add(scanner.nextLong());
	}

}
