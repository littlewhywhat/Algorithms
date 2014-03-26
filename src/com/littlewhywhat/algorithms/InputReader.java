package com.littlewhywhat.algorithms;

public interface InputReader<ConfigType,DataType> {
	void read();
	ConfigType getConfig();
	DataType getData();
}
