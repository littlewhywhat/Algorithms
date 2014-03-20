package com.littlewhywhat.algorithms.cakedivider.test;

import org.junit.Test;

import com.littlewhywhat.algorithms.cakedivider.CakeDividerOutputWriter;
import com.littlewhywhat.algorithms.test.TestHelper;

public class TestCakeDividerOutputWriter {
	private CakeDividerOutputWriter writer = new CakeDividerOutputWriter();
	private static final String FOLDER = "OutputTest/";
	private static final String ANSWER_FILEPATH = FOLDER + "answer.txt";
	
	public static final String[] RIGHT_ANSWERS = new String[] { "1 1","45"};
	public static final String[] WRONG_ANSWER = new String[] { "-1" };
	@Test
	public void testOutputRightAnswer() {
		writer.setOutput(RIGHT_ANSWERS);
		writer.setOutputFilePath(ANSWER_FILEPATH);
		writer.write();
		TestHelper.checkAnswerOutput(RIGHT_ANSWERS, ANSWER_FILEPATH);
	}
	@Test
	public void testOutputWrongAnswer() {
		writer.setOutput(WRONG_ANSWER);
		writer.setOutputFilePath(ANSWER_FILEPATH);
		writer.write();
		TestHelper.checkAnswerOutput(WRONG_ANSWER, ANSWER_FILEPATH);
	}

}
