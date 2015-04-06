package com.littlewhywhat.algorithms.test;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;
import com.littlewhywhat.algorithms.io.txt.TextFileOutputWriter;

public class TestAbstractAlgorithm {
	Reader reader = new Reader();
	Writer writer = new Writer();
	MockAbstractAlgorithm algorithm = new MockAbstractAlgorithm();
<<<<<<< HEAD:src/com/littlewhywhat/algorithms/test/TestAbstractAlgorithm.java
	private static final String FOLDER = "src/com/littlewhywhat/algorithms/test/input/";
	private static final String INPUT_PATH = FOLDER + "test.txt";
	private static final String OUTPUT_PATH = FOLDER + "output.txt";
=======
	private static final String FOLDER = "algorithms/abstractalgorithm/";
	private static final String INPUT_PATH = FOLDER + "test.txt";
	private static final String OUTPUT_PATH = "build/tmp/test/output.txt";
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/TestAbstractAlgorithm.java
	private static final String[] INPUT = new String[] { "config", "data" };
	public static final String[] OUTPUT = new String[] { "OUTPUT", "TEST" };

	@Test
	public void testExecuteWithReadWrite() {
		reader.setInputFilePath(INPUT_PATH);
		writer.setOutputFilePath(OUTPUT_PATH);
<<<<<<< HEAD:src/com/littlewhywhat/algorithms/test/TestAbstractAlgorithm.java
		algorithm.executeWithReadWrite(reader, writer);
=======
		reader.read();
		algorithm.setConfig(reader.getConfig());
		algorithm.setData(reader.getData());
		algorithm.execute();
		writer.setOutput(algorithm.getOutput());
		writer.write();
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/TestAbstractAlgorithm.java
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
