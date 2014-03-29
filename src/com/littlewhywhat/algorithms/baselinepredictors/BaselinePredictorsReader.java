package com.littlewhywhat.algorithms.baselinepredictors;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;
import com.littlewhywhat.datastructure.ArrayPart;

public class BaselinePredictorsReader extends
		TextFileInputReader<Integer, BaselinePredictorsData> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		extractConfig(scanner);
		extractData(scanner);
	}

	private void extractData(Scanner scanner) {
		int[][] matrix = new int[scanner.nextInt()][scanner.nextInt()];	
		MatrixIndex[] learnData = new MatrixIndex[scanner.nextInt()];
		MatrixIndex[] testData = new MatrixIndex[scanner.nextInt()];
		for (int i = 0; i < learnData.length; i++) {
			learnData[i] = new MatrixIndex(scanner.nextInt(), scanner.nextInt());			
			matrix[learnData[i].getUserIndex()][learnData[i].getItemIndex()] = scanner.nextInt();
		}
		for (int i = 0; i < testData.length; i++)
			testData[i] = new MatrixIndex(scanner.nextInt(), scanner.nextInt());
		BaselinePredictorsAlgorithm.Data algoData = new BaselinePredictorsAlgorithm.Data(matrix, ArrayPart.getInstance(testData));
		setData(new BaselinePredictorsData(learnData, algoData));
	}

	private void extractConfig(Scanner scanner) {	
		setConfig(scanner.nextInt());
	}

}
