package com.littlewhywhat.algorithms;

import com.littlewhywhat.algorithms.io.InputReader;
import com.littlewhywhat.algorithms.io.OutputWriter;

public abstract class AbstractAlgorithm<ConfigType, DataType, OutputType>
		implements Algorithm<ConfigType, DataType, OutputType> {
	private ConfigType config;
	private DataType data;
	private OutputType output;
	
	protected void setOutput(OutputType output) {
		this.output = output;
	}
	
	@Override
	public void setConfig(ConfigType config) {
		this.config = config;		
	}

	@Override
	public void setData(DataType data) {
		this.data = data;
	}

	public ConfigType getConfig() {
		return config;
	}

	public DataType getData() {
		return data;
	}

	@Override
	public OutputType getOutput() {
		return this.output;
	}

	public void executeWithReadWrite(
			InputReader<ConfigType, DataType> inputReader,
			OutputWriter<OutputType> outputWriter) {
		inputReader.read();
		setConfig(inputReader.getConfig());
		setData(inputReader.getData());
		execute();
		outputWriter.setOutput(getOutput());
		outputWriter.write();
	}

}
