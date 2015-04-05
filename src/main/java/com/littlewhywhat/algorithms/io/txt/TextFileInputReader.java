package com.littlewhywhat.algorithms.io.txt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class TextFileInputReader<ConfigData, DataType> extends
		AbstractFileInputReader<ConfigData, DataType> {

	@Override
	public void readFile(File file) {
		try (Scanner scanner = new Scanner(file)) {
			extractInputData(scanner);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + this.getInputFilePath());
		} catch (InputMismatchException e) {
			System.out.println("Unable to read file: " + this.getInputFilePath());
		}
	}

	protected abstract void extractInputData(Scanner scanner)
			throws InputMismatchException;

}
