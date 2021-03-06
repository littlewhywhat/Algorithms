package com.littlewhywhat.algorithms.io;

public abstract class AbstractOutputWriter<OutputType> implements OutputWriter<OutputType> {

	private OutputType output;
	@Override
	public void setOutput(OutputType output) {
		this.output = output;
	}
	protected OutputType getOutput() {
		return output;
	}
}
