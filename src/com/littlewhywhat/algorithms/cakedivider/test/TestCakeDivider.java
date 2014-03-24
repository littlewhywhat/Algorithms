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
	private static final String FILE_THAT_HAS_ANSWER_CUSTOM_ONE = FOLDER
			+ "testCustom1.txt";
	private static final String FILE_THAT_HAS_ANSWER_CUSTOM_TWO = FOLDER
			+ "testCustom2.txt";
	private static final String FILE_THAT_HAS_NO_ANSWER = FOLDER
			+ "testWithNoAnswer.txt";
	private static final String FILE_THAT_HAS_ANSWER_CUSTOM_THREE = FOLDER
			+ "testCustom3.txt";
	private static final String FILE_THAT_HAS_BIG_INPUT = FOLDER
			+ "testBig.txt";
	private static final int MAX_CAKE_VALUE = 1500;

	@Test
	public void testExecuteWithReadWriteCustomOne() {
		testExecuteWithReadWrite(FILE_THAT_HAS_ANSWER_CUSTOM_ONE,
				TestAnswersCakedDivider.CUSTOM_ONE_ANSWERS);
	}

	@Test
	public void testExecuteWithReadWriteCustomTwo() {
		testExecuteWithReadWrite(FILE_THAT_HAS_ANSWER_CUSTOM_TWO,
				TestAnswersCakedDivider.CUSTOM_TWO_ANSWERS);
	}

	@Test
	public void testExecuteWithReadWriteCustomThree() {
		testExecuteWithReadWrite(FILE_THAT_HAS_ANSWER_CUSTOM_THREE,
				TestAnswersCakedDivider.CUSTOM_THREE_ANSWERS);
	}

	@Test
	public void testExecuteWithReadWriteRightOutput() {
		testExecuteWithReadWrite(FILE_THAT_HAS_ANSWER_SQUARE,
				TestAnswersCakedDivider.RIGHT_ANSWERS);
	}

	@Test
	public void testExecuteWithReadWriteWrongOutput() {
		testExecuteWithReadWrite(FILE_THAT_HAS_NO_ANSWER,
				TestAnswersCakedDivider.WRONG_ANSWER);
	}
	
	@Test
	public void testSpeed() {
		CakeGenerator.writeCake(FILE_THAT_HAS_BIG_INPUT, CakeGenerator.generateSquareCake(MAX_CAKE_VALUE));
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++)
			testExecuteWithReadWrite(FILE_THAT_HAS_BIG_INPUT, TestAnswersCakedDivider.BIG_ANSWERS);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	private void testExecuteWithReadWrite(String fileInputPath, String[] answers) {
		reader.setInputFilePath(fileInputPath);
		writer.setOutputFilePath(OUTPUT_FILE_PATH);
		divider.executeWithReadWrite(reader, writer);
		TestHelper.checkAnswerOutput(answers, OUTPUT_FILE_PATH);
	}

}
