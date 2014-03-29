package com.littlewhywhat.algorithms.baselinepredictors;

import java.util.InputMismatchException;
import java.util.Scanner;

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
		PredictionData.MatrixIndex[] learnData = new PredictionData.MatrixIndex[scanner.nextInt()];
		PredictionData.MatrixIndex[] testData = new PredictionData.MatrixIndex[scanner.nextInt()];
		for (int i = 0; i < learnData.length; i++) {
			learnData[i] = new PredictionData.MatrixIndex(scanner.nextInt(), scanner.nextInt());			
			matrix[learnData[i].getUserIndex()][learnData[i].getItemIndex()] = scanner.nextInt();
		}
		for (int i = 0; i < testData.length; i++)
			testData[i] = new PredictionData.MatrixIndex(scanner.nextInt(), scanner.nextInt());
		setData(new PredictionData(learnData, testData, matrix));
	}

	private void extractConfig(Scanner scanner) {	
		setConfig(scanner.nextInt());
	}

}
