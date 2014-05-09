package com.littlewhywhat.algorithms.io.txt;

import org.junit.Test;

import com.littlewhywhat.algorithms.cakedivider.test.TestAnswersCakedDivider;
import com.littlewhywhat.algorithms.test.TestHelper;

public class TestStringArrayOutputWriter {
	private StringArrayOutputWriter writer = new StringArrayOutputWriter();
	private static final String FOLDER = "src/com/littlewhywhat/algorithms/io/txt/";
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
