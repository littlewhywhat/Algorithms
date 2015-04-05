package com.littlewhywhat.algorithms.io.txt;

import java.io.BufferedWriter;
import java.io.IOException;

public class StringArrayOutputWriter extends TextFileOutputWriter<String[]> {
	@Override
	protected void writeOutputData(BufferedWriter bw) throws IOException {
		for (String line : getOutput()) {
			bw.write(line);
			bw.newLine();
		}
	}

}
