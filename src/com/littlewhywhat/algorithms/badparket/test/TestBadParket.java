package com.littlewhywhat.algorithms.badparket.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.badparket.BadParket;
import com.littlewhywhat.algorithms.badparket.Data;
import com.littlewhywhat.algorithms.badparket.ParketReader;
import com.littlewhywhat.algorithms.badparket.ParketWriter;

public class TestBadParket {
	public static final String FOLDER = "BadParket/";
	public static final String INPUTFILE = FOLDER +  "input.txt";
	public static final String INPUTFILE_TWO = FOLDER +  "input2.txt";
	private static final ParketReader reader = new ParketReader();
	public static final boolean[][] isBad = new boolean[][] { { false, true, true }, { false, true, false } };
	private static final String OUTPUTFILE = FOLDER + "output.txt";
	
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
		writer.setOutputFilePath(OUTPUTFILE);
		for (int i = 0; i < 10000; i++)
			parket.executeWithReadWrite(getReader(), writer );
	}
	
	@Test
	public void testExecuteTwo() {
		writer.setOutputFilePath(OUTPUTFILE);
		reader.setInputFilePath(INPUTFILE_TWO);
		parket.executeWithReadWrite(reader, writer );
	}
}
