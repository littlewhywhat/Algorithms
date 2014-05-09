package com.littlewhywhat.algorithms.cakedivider.test;

import org.junit.Assert;
import org.junit.Test;

import com.littlewhywhat.algorithms.cakedivider.CakeDividerInputReader;

public class TestCakeDividerInputReader {
	
	CakeDividerInputReader reader = new CakeDividerInputReader();

	@Test
	public void testInputCorrectFile() {
		reader.setInputFilePath(TestCakeDivider.FOLDER + "testSquare.txt");
		reader.read();
		Assert.assertEquals(4, reader.getData().length);
		Assert.assertEquals(0, reader.getData()[0].getX(), 0);
		Assert.assertEquals(0, reader.getData()[0].getY(), 0);
		Assert.assertEquals(0, reader.getData()[3].getX(), 0);
		Assert.assertEquals(2, reader.getData()[3].getY(), 0);
	}

	@Test
	public void testInputCorruptCountLinesOne() {
		reader.setInputFilePath(TestCakeDivider.FOLDER + "corruptCountLinesOne.txt");
		reader.read();
	}

	@Test
	public void testInputCorruptFirstLine() {
		reader.setInputFilePath(TestCakeDivider.FOLDER + "corruptFirstLine.txt");
		reader.read();
	}

	@Test
	public void testInputCorruptOtherLines() {
		reader.setInputFilePath(TestCakeDivider.FOLDER + "corruptOtherLines.txt");
		reader.read();
	}

	@Test
	public void testInputWrongFileName() {
		reader.setInputFilePath(TestCakeDivider.FOLDER + "t");
		reader.read();
	}

}
