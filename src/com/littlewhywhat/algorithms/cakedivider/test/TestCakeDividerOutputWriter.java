package com.littlewhywhat.algorithms.cakedivider.test;

import org.junit.Test;

import com.littlewhywhat.algorithms.cakedivider.CakeDividerOutputWriter;
import com.littlewhywhat.algorithms.test.TestHelper;

public class TestCakeDividerOutputWriter {
	private CakeDividerOutputWriter writer = new CakeDividerOutputWriter();
	private static final String FOLDER = "OutputTest/";
	private static final String ANSWER_FILEPATH = FOLDER + "answer.txt";
	
	@Test
	public void testOutputRightAnswer() {
		testOutput(TestAnswersCakedDivider.RIGHT_ANSWERS);
	}
	@Test
	public void testOutputWrongAnswer() {
		testOutput(TestAnswersCakedDivider.WRONG_ANSWER);
	}
	
	private void testOutput(String[] output) {
		writer.setOutput(output);
		writer.setOutputFilePath(ANSWER_FILEPATH);
		writer.write();
		TestHelper.checkAnswerOutput(output, ANSWER_FILEPATH);
	}
}
