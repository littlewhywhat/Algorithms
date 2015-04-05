package com.littlewhywhat.algorithms.io.txt;

import java.io.InputStream;

import java.lang.NullPointerException;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class TextFileInputReader<ConfigData, DataType> extends
		AbstractFileInputReader<ConfigData, DataType> {

	@Override
	public void readFile(InputStream file) {
		try (Scanner scanner = new Scanner(file)) {
			extractInputData(scanner);
		} catch (NullPointerException e) {
			System.out.println("File not found: " + this.getInputFilePath());
		} catch (InputMismatchException e) {
			System.out.println("Unable to read file: " + this.getInputFilePath());
			e.printStackTrace();
		}
	}

	protected abstract void extractInputData(Scanner scanner)
			throws InputMismatchException;

}
