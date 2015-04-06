package com.littlewhywhat.algorithms.io.txt;

<<<<<<< HEAD:src/com/littlewhywhat/algorithms/io/txt/TextFileInputReader.java
import java.io.File;
import java.io.FileNotFoundException;
=======
import java.io.InputStream;

import java.lang.NullPointerException;

>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/io/txt/TextFileInputReader.java
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class TextFileInputReader<ConfigData, DataType> extends
		AbstractFileInputReader<ConfigData, DataType> {

	@Override
<<<<<<< HEAD:src/com/littlewhywhat/algorithms/io/txt/TextFileInputReader.java
	public void readFile(File file) {
		try (Scanner scanner = new Scanner(file)) {
			extractInputData(scanner);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + this.getInputFilePath());
		} catch (InputMismatchException e) {
			System.out.println("Unable to read file: " + this.getInputFilePath());
=======
	public void readFile(InputStream file) {
		try (Scanner scanner = new Scanner(file)) {
			extractInputData(scanner);
		} catch (NullPointerException e) {
			System.out.println("File not found: " + this.getInputFilePath());
		} catch (InputMismatchException e) {
			System.out.println("Unable to read file: " + this.getInputFilePath());
			e.printStackTrace();
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/io/txt/TextFileInputReader.java
		}
	}

	protected abstract void extractInputData(Scanner scanner)
			throws InputMismatchException;

}
