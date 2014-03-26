package com.littlewhywhat.algorithms;

public interface Algorithm<ConfigType, DataType, OutputType> {
	void setConfig(ConfigType configLine);
	void setData(DataType data);
	void execute();
	OutputType getOutput();
}
