package com.littlewhywhat.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class TextFileInputReader<ConfigData, DataType> extends
		AbstractInputReader<ConfigData, DataType> {

	private String inputFilePath;

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	@Override
	public void read() {
		File file = new File(inputFilePath);
		try (Scanner scanner = new Scanner(file)) {
			extractInputData(scanner);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputFilePath);
		} catch (InputMismatchException e) {
			System.out.println("Unable to read file: " + inputFilePath);
		}
	}

	protected abstract void extractInputData(Scanner scanner)
			throws InputMismatchException;

}
