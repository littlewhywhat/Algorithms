package com.littlewhywhat.algorithms.baselinepredictors.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictors;
import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsReader;
import com.littlewhywhat.algorithms.io.txt.StringArrayOutputWriter;



public class TestBaselinePredictors {

	public static final String FOLDER = "src/com/littlewhywhat/algorithms/baselinepredictors/test/input/";
	public static final String INPUT_FILE_PATH = FOLDER + "input.txt";
	public static final String OUTPUT_FILE_PATH = FOLDER + "output.txt";
	private BaselinePredictors bp;
	private BaselinePredictorsReader reader;
	private StringArrayOutputWriter writer;
	
	
	@Before
	public void setUp() throws Exception {
		bp = new BaselinePredictors();
		writer = new StringArrayOutputWriter();
		reader = new BaselinePredictorsReader();
		writer.setOutputFilePath(OUTPUT_FILE_PATH);
		reader.setInputFilePath(INPUT_FILE_PATH);
	}

	@Test
	public void testExecuteWithReadWrite() {
		bp.executeWithReadWrite(reader, writer);
	}

}
