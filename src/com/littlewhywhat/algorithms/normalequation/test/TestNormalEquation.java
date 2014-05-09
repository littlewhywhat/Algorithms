package com.littlewhywhat.algorithms.normalequation.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.io.txt.StringArrayOutputWriter;
import com.littlewhywhat.algorithms.normalequation.NormalEquation;
import com.littlewhywhat.algorithms.normalequation.NormalEquationReader;


public class TestNormalEquation {

	private NormalEquation equation;
	private NormalEquationReader reader;
	private StringArrayOutputWriter writer;
	public static final String FOLDER = "src/com/littlewhywhat/algorithms/normalequation/test/input/";
	public static final String INPUT_FILE_PATH = FOLDER + "input.txt";
	public static final String OUTPUT_FILE_PATH = FOLDER + "output.txt";
	
	@Before
	public void setUp() throws Exception {
		equation = new NormalEquation();
		reader = new NormalEquationReader();
		reader.setInputFilePath(INPUT_FILE_PATH);
		writer = new StringArrayOutputWriter();
		writer.setOutputFilePath(OUTPUT_FILE_PATH);
	}

	@Test
	public void testExecuteWithReadWrite() {
		equation.executeWithReadWrite(reader, writer);
	}

}
