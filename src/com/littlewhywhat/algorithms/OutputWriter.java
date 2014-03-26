package com.littlewhywhat.algorithms;

public interface OutputWriter<OutputType> {
	void setOutput(OutputType output);
	void write();
}
