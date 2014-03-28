package com.littlewhywhat.algorithms.baselinepredictors;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.baselinepredictors.PredictionData.UserItemIndex;
import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class BaselinePredictorsReader extends
		TextFileInputReader<Integer, PredictionData> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		extractConfig(scanner);
		extractData(scanner);
	}

	private void extractData(Scanner scanner) {
		int[][] matrix = new int[scanner.nextInt()][scanner.nextInt()];	
		UserItemIndex[] learnData = new UserItemIndex[scanner.nextInt()];
		UserItemIndex[] testData = new UserItemIndex[scanner.nextInt()];
		for (int i = 0; i < learnData.length; i++) {
			learnData[i] = new UserItemIndex(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			matrix[learnData[i].getUserId()][learnData[i].getItemId()] = learnData[i].getValue();
		}
		for (int i = 0; i < testData.length; i++)
			testData[i] = new UserItemIndex(scanner.nextInt(), scanner.nextInt(), 0);
		setData(new PredictionData(learnData, testData, matrix));
	}

	private void extractConfig(Scanner scanner) {	
		setConfig(scanner.nextInt());
	}

}
