package com.littlewhywhat.algorithms.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.TextFileInputReader;
import com.littlewhywhat.algorithms.TextFileOutputWriter;

public class TestAbstractAlgorithm {
	Reader reader = new Reader();
	Writer writer = new Writer();
	MockAbstractAlgorithm algorithm = new MockAbstractAlgorithm();
	private static final String FOLDER = "AbstractAlgorithmTest/";
	private static final String INPUT_PATH = FOLDER + "test.txt";
	private static final String OUTPUT_PATH = FOLDER + "output.txt";
	private static final String[] INPUT = new String[] { "config", "data" };
	public static final String[] OUTPUT = new String[] { "OUTPUT", "TEST" };

	@Test
	public void testExecuteWithReadWrite() {
		reader.setInputFilePath(INPUT_PATH);
		writer.setOutputFilePath(OUTPUT_PATH);
		algorithm.executeWithReadWrite(reader, writer);
		Assert.assertEquals(INPUT[0], algorithm.getConfig());
		Assert.assertEquals(INPUT[1], algorithm.getData()[0]);
		TestHelper.checkAnswerOutput(OUTPUT, OUTPUT_PATH);
	}

	private class Reader extends TextFileInputReader<String, String[]> {
		@Override
		protected void extractInputData(Scanner scanner)
				throws InputMismatchException {
			setConfig(scanner.next());
			setData(new String[] { scanner.next() });
		}
	}

	private class Writer extends TextFileOutputWriter<String[]> {
		@Override
		protected void writeOutputData(BufferedWriter bw) throws IOException {
			for (String line : getOutput()) {
				bw.write(line);
				bw.newLine();
			}
		}
	}

	private class MockAbstractAlgorithm extends
			AbstractAlgorithm<String, String[], String[]> {
		@Override
		public void execute() {
			setOutput(OUTPUT);
		}

	}
}
