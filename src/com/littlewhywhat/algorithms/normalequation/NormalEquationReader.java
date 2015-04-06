package com.littlewhywhat.algorithms.normalequation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.math3.linear.MatrixUtils;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class NormalEquationReader extends
		TextFileInputReader<Integer[], DataNormalEquation> {

	private int numLearningRows;
	private int numTestingRows;
	private int numParams;
	
	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		extractConfig(scanner);
		extractData(scanner);
	}

	private void extractData(Scanner scanner) {
		double[][] learnValues = new double[numLearningRows][numParams];
		double[] learnResults = new double[numLearningRows];
		double[][] testValues = new double[numTestingRows][numParams];
		try {
			for (int i = 0; i < numLearningRows; i++) {
				learnValues[i][0] = 1;
				for (int j = 1; j < numParams; j++) 
					learnValues[i][j] = scanner.nextInt();
				learnResults[i] = scanner.nextInt();
			}
			for (int i = 0; i < numTestingRows; i++) {
				testValues[i][0] = 1;
				for (int j = 1; j < numParams; j++)
					testValues[i][j] = scanner.nextInt();
			}
		} catch (NoSuchElementException e) {
			throw new InputMismatchException(e.getMessage());
		}
		DataNormalEquation data = new DataNormalEquation();
		data.setLearnResults(MatrixUtils.createRealVector(learnResults));
		data.setLearnValues(MatrixUtils.createRealMatrix(learnValues));
		data.setTestValues(MatrixUtils.createRealMatrix(testValues));
		setData(data);		
	}

	private void extractConfig(Scanner scanner) {
		List<Integer> paramsList =  readIntegersLine(scanner.nextLine());
		numTestingRows = paramsList.remove(paramsList.size() - 1);
		numLearningRows = paramsList.remove(paramsList.size() - 1);
		numParams = paramsList.size();
		setConfig(paramsList.toArray(new Integer[numParams - 1]));
	}
	
	private List<Integer> readIntegersLine(String line) {
		List<Integer> paramsList = new ArrayList<Integer>();
		try (Scanner scanner = new Scanner(line)) {
			while (scanner.hasNext())
				paramsList.add(scanner.nextInt());
		}
		return paramsList;
	}

}
