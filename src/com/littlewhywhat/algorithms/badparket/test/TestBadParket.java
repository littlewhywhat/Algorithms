package com.littlewhywhat.algorithms.badparket.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.badparket.BadParket;
import com.littlewhywhat.algorithms.badparket.Data;
import com.littlewhywhat.algorithms.badparket.ParketReader;
import com.littlewhywhat.algorithms.badparket.ParketWriter;
import com.littlewhywhat.algorithms.test.TestHelper;

public class TestBadParket {
	public static final String FOLDER = "BadParket/";
	public static final String INPUTFILE = FOLDER + "input.txt";
	public static final String INPUTFILE_TWO = FOLDER + "input2.txt";
	public static final String INPUTFILE_BIG = FOLDER + "input_big.txt";
	public static final String INPUTFILE_BIG_ALL_BAD = FOLDER
			+ "input_big_all_bad.txt";
	private static final ParketReader reader = new ParketReader();
	// private static final BadParketGenerator generator = new
	// BadParketGenerator();
	public static final boolean[][] isBad = new boolean[][] {
			{ false, true, true }, { false, true, false } };
	private static final String OUTPUTFILE = FOLDER + "output.txt";
	private static final String INPUTFILE_BIG_SCOT = FOLDER
			+ "input_big_scott.txt";

	public BadParket parket;
	private ParketWriter writer;

	public static ParketReader getReader() {
		reader.setInputFilePath(INPUTFILE);
		return reader;
	}

	public static Data getReaderData() {
		getReader().read();
		return reader.getData();
	}

	@Before
	public void setUp() throws Exception {
		parket = new BadParket();
		writer = new ParketWriter();
	}

	@Test
	public void testExecute() throws InterruptedException {
		testWithInput(INPUTFILE, new String[] { "5" });
	}

	@Test
	public void testExecuteTwo() {
		testWithInput(INPUTFILE_TWO, new String[] { "34" });
	}

	@Test
	public void testExecuteBig() {
		testWithInput(INPUTFILE_BIG, new String[] { "20644400" });
	}

	@Test
	public void testExecuteBigAllBad() {
		testWithInput(INPUTFILE_BIG_ALL_BAD, new String[] { "40500000" });
	}

	@Test
	public void testExecuteBigScot() {
		testWithInput(INPUTFILE_BIG_SCOT, new String[] { "22500000" });
	}

	private void testWithInput(String inputFile, String[] answers) {
		writer.setOutputFilePath(OUTPUTFILE);
		reader.setInputFilePath(inputFile);
		parket.executeWithReadWrite(reader, writer);
		TestHelper.checkAnswerOutput(answers, OUTPUTFILE);
	}
}
