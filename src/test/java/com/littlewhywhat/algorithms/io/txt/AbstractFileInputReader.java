package com.littlewhywhat.algorithms.io.txt;

import java.io.InputStream;

import com.littlewhywhat.algorithms.io.AbstractInputReader;

public abstract class AbstractFileInputReader<ConfigType, DataType> extends
		AbstractInputReader<ConfigType, DataType> implements
		FileInputReader<ConfigType, DataType> {

	private String inputFilePath;

	protected String getInputFilePath() {
		return this.inputFilePath;
	}

	@Override
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	@Override
	public void read() {
		readFile(this.getClass().getClassLoader().getResourceAsStream(inputFilePath));
	}

	protected abstract void readFile(InputStream file);

}
