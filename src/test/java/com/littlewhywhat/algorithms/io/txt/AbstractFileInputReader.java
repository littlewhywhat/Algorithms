package com.littlewhywhat.algorithms.io.txt;

<<<<<<< HEAD:src/com/littlewhywhat/algorithms/io/txt/AbstractFileInputReader.java
import java.io.File;
=======
import java.io.InputStream;
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/io/txt/AbstractFileInputReader.java

import com.littlewhywhat.algorithms.io.AbstractInputReader;

public abstract class AbstractFileInputReader<ConfigType, DataType> extends
		AbstractInputReader<ConfigType, DataType> implements
		FileInputReader<ConfigType, DataType> {

	private String inputFilePath;

	protected String getInputFilePath() {
		return this.inputFilePath;
	}

	@Override
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	@Override
	public void read() {
<<<<<<< HEAD:src/com/littlewhywhat/algorithms/io/txt/AbstractFileInputReader.java
		readFile(new File(inputFilePath));
	}

	protected abstract void readFile(File file);
=======
		readFile(this.getClass().getClassLoader().getResourceAsStream(inputFilePath));
	}

	protected abstract void readFile(InputStream file);
>>>>>>> 261a4fc613ddf1a5618e40849b5110012513a1fc:src/test/java/com/littlewhywhat/algorithms/io/txt/AbstractFileInputReader.java

}
