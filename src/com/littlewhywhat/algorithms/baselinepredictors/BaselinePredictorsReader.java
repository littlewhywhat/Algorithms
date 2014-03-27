package com.littlewhywhat.algorithms.baselinepredictors;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.UserItemEntry;
import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class BaselinePredictorsReader extends
		TextFileInputReader<PredictionConfig, PredictionData> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		extractConfig(scanner);
		extractData(scanner);
	}

	private void extractData(Scanner scanner) {
		UserItemEntry[] learnData = new UserItemEntry[scanner.nextInt()];
		UserItemEntry[] testData = new UserItemEntry[scanner.nextInt()];
		for (int i = 0; i < learnData.length; i++)
			learnData[i] = new UserItemEntry(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		for (int i = 0; i < testData.length; i++)
			testData[i] = new UserItemEntry(scanner.nextInt(), scanner.nextInt(), 0);
		setData(new PredictionData(learnData, testData));
	}

	private void extractConfig(Scanner scanner) {	
		setConfig(new PredictionConfig(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
	}

}
