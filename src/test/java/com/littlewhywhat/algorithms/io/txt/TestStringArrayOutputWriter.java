package com.littlewhywhat.algorithms.io.txt;

import org.junit.Test;

<<<<<<< HEAD:src/com/littlewhywhat/algorithms/io/txt/TestStringArrayOutputWriter.java
import com.littlewhywhat.algorithms.cakedivider.test.TestAnswersCakedDivider;
=======
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/io/txt/TestStringArrayOutputWriter.java
import com.littlewhywhat.algorithms.test.TestHelper;

public class TestStringArrayOutputWriter {
	private StringArrayOutputWriter writer = new StringArrayOutputWriter();
<<<<<<< HEAD:src/com/littlewhywhat/algorithms/io/txt/TestStringArrayOutputWriter.java
	private static final String FOLDER = "src/com/littlewhywhat/algorithms/io/txt/";
=======
	private static final String FOLDER = "build/tmp/test/";
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/io/txt/TestStringArrayOutputWriter.java
	private static final String ANSWER_FILEPATH = FOLDER + "answer.txt";
	
	@Test
	public void testOutputRightAnswer() {
<<<<<<< HEAD:src/com/littlewhywhat/algorithms/io/txt/TestStringArrayOutputWriter.java
		testOutput(TestAnswersCakedDivider.RIGHT_ANSWERS);
	}
	@Test
	public void testOutputWrongAnswer() {
		testOutput(TestAnswersCakedDivider.WRONG_ANSWER);
=======
		testOutput(new String[] { "1 1","45"});
	}
	@Test
	public void testOutputWrongAnswer() {
		testOutput(new String[] { "-1" });
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/io/txt/TestStringArrayOutputWriter.java
	}
	
	private void testOutput(String[] output) {
		writer.setOutput(output);
		writer.setOutputFilePath(ANSWER_FILEPATH);
		writer.write();
		TestHelper.checkAnswerOutput(output, ANSWER_FILEPATH);
	}
}
