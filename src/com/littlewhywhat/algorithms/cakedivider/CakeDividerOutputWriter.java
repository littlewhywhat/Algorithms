package com.littlewhywhat.algorithms.cakedivider;

import java.io.BufferedWriter;
import java.io.IOException;

import com.littlewhywhat.algorithms.TextFileOutputWriter;

public class CakeDividerOutputWriter extends TextFileOutputWriter<String[]> {
	@Override
	protected void writeOutputData(BufferedWriter bw) throws IOException {
		for (String line : getOutput()) {
			bw.write(line);
			bw.newLine();
		}
	}

}
