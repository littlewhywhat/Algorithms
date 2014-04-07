package com.littlewhywhat.algorithms.badparket;

import java.io.BufferedWriter;
import java.io.IOException;

import com.littlewhywhat.algorithms.io.txt.TextFileOutputWriter;

public class ParketWriter extends TextFileOutputWriter<Integer> {

	@Override
	protected void writeOutputData(BufferedWriter bw) throws IOException {
		bw.write(String.valueOf(getOutput()));
		
	}

}
