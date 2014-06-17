package com.littlewhywhat.algorithms.io.txt;

import com.littlewhywhat.algorithms.io.InputReader;

public interface FileInputReader<ConfigType, DataType> extends
		InputReader<ConfigType, DataType> {

	void setInputFilePath(String inputFilePath);
}
