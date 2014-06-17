package com.littlewhywhat.algorithms.io.txt;

import java.io.File;

import com.littlewhywhat.algorithms.io.AbstractInputReader;

public abstract class AbstractFileInputReader<ConfigType, DataType> extends AbstractInputReader<ConfigType, DataType> {

	private String inputFilePath;

	protected String getInputFilePath() {
		return this.inputFilePath;
	}
	
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}
	
	@Override
	public void read() {
		readFile(new File(inputFilePath));
	}

	protected abstract void readFile(File file);

}
