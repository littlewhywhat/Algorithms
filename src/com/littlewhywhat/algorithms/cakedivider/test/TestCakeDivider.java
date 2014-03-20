package com.littlewhywhat.algorithms.cakedivider.test;

import org.junit.Test;

import com.littlewhywhat.algorithms.cakedivider.CakeDivider;
import com.littlewhywhat.algorithms.cakedivider.CakeDividerInputReader;
import com.littlewhywhat.algorithms.cakedivider.CakeDividerOutputWriter;
import com.littlewhywhat.algorithms.test.TestHelper;

public class TestCakeDivider {
	CakeDividerInputReader reader = new CakeDividerInputReader();
	CakeDividerOutputWriter writer = new CakeDividerOutputWriter();
	CakeDivider divider = new CakeDivider();
	private static final String FOLDER = "CakeDividerTest/";
	private static final String OUTPUT_FILE_PATH = FOLDER + "answer.txt";	
	private static final String FILE_THAT_HAS_ANSWER_SQUARE = "InputTest/testSquare.txt";
	private static final String FILE_THAT_HAS_ANSWER_CUSTOM_ONE = FOLDER + "testCustom1.txt";
	private static final String FILE_THAT_HAS_ANSWER_CUSTOM_TWO = FOLDER + "testCustom2.txt";
	private static final String FILE_THAT_HAS_NO_ANSWER = FOLDER + "testWithNoAnswer.txt";
	
	
	@Test
	public void testExecuteWithReadWriteCustomOne() {
		reader.setInputFilePath(FILE_THAT_HAS_ANSWER_CUSTOM_ONE);
		writer.setOutputFilePath(OUTPUT_FILE_PATH);
		divider.executeWithReadWrite(reader, writer);
		TestHelper.checkAnswerOutput(new String[] { "1 2", "45" }, OUTPUT_FILE_PATH);
	}
	
	@Test
	public void testExecuteWithReadWriteCustomTwo() {
		reader.setInputFilePath(FILE_THAT_HAS_ANSWER_CUSTOM_TWO);
		writer.setOutputFilePath(OUTPUT_FILE_PATH);
		divider.executeWithReadWrite(reader, writer);
		TestHelper.checkAnswerOutput(new String[] { "1 5", "45" }, OUTPUT_FILE_PATH);
	}
	
	@Test
	public void testExecuteWithReadWriteRightOutput() {
		reader.setInputFilePath(FILE_THAT_HAS_ANSWER_SQUARE);
		writer.setOutputFilePath(OUTPUT_FILE_PATH);
		divider.executeWithReadWrite(reader, writer);
		TestHelper.checkAnswerOutput(TestCakeDividerOutputWriter.RIGHT_ANSWERS, OUTPUT_FILE_PATH);
	}
	
	@Test
	public void testExecuteWithReadWriteWrongOutput() {
		reader.setInputFilePath(FILE_THAT_HAS_NO_ANSWER);
		writer.setOutputFilePath(OUTPUT_FILE_PATH);
		divider.executeWithReadWrite(reader, writer);
		TestHelper.checkAnswerOutput(TestCakeDividerOutputWriter.WRONG_ANSWER, OUTPUT_FILE_PATH);
	}


}
