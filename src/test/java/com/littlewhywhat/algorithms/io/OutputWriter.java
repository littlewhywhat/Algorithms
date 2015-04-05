package com.littlewhywhat.algorithms.io;

public interface OutputWriter<OutputType> {
	void setOutput(OutputType output);
	void write();
}
