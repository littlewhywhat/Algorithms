package com.littlewhywhat.algorithms.io;

public interface InputReader<ConfigType,DataType> {
	void read();
	ConfigType getConfig();
	DataType getData();
}
