package com.littlewhywhat.algorithms.io.txt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.littlewhywhat.algorithms.io.AbstractOutputWriter;

public abstract class TextFileOutputWriter<OutputType> extends
		AbstractOutputWriter<OutputType> {

	private String outputFilePath;

	public void setOutputFilePath(String fileOutputPath) {
		this.outputFilePath = fileOutputPath;
	}

	@Override
	public void write() {
		File file = new File(outputFilePath);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			writeOutputData(bw);
		} catch (IOException e) {
			System.out.println("Unable to write file: " + file.getName());
		}

	}

	protected abstract void writeOutputData(BufferedWriter bw)
			throws IOException;
}
