package com.littlewhywhat.algorithms.badparket.test;

import java.util.Random;

import com.littlewhywhat.algorithms.io.txt.StringArrayOutputWriter;
import com.littlewhywhat.datastructure.collection.CycleResolver;

public class BadParketGenerator {
	private String[] parketValues = new String[] { "*", "."};
	private Random random = new Random();
	private StringArrayOutputWriter writer = new StringArrayOutputWriter();
	private CycleResolver resolver = new CycleResolver(2);
	private int xSize;
	private int ySize;
	private int twoCost;
	private int oneCost;
	private boolean isRandom = true;
	
	public void setParketValues(String[] parketValues) {
		this.parketValues = parketValues;
	}
	
	public void setConfig(int xSize, int ySize, int twoCost, int oneCost) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.twoCost = twoCost;
		this.oneCost = oneCost;
	}
	
	public void setOutputFilePath(String outputFilePath) {
		this.writer.setOutputFilePath(outputFilePath);
	}
	
	public void generate() {
		this.writer.setOutput(getOutput());
		this.writer.write();
	}

	private String[] getOutput() {
		String[] output = new String[xSize + 1];
		output[0] = xSize + " " + ySize + " " + twoCost + " " + oneCost;
		fillArrayByBadParket(output);
		return output;
	}

	private void fillArrayByBadParket(String[] output) {
		for (int i = 1; i < output.length; i++)
			if (this.isRandom)
				output[i] = getRandomBadParketLine();
			else
				output[i] = getPatternBadParketLine();
	}

	private String getPatternBadParketLine() {
		turnValues();
		String line = "";
		for (int i=0; i < ySize; i++)
			line += parketValues[resolver.resolveIndex(i)];
		return line;
	}

	private void turnValues() {
		String[] temp = parketValues.clone();
		parketValues[0] = temp[1];
		parketValues[1] = temp[0];
		
	}

	private String getRandomBadParketLine() {
		String badParketLine = "";
		for (int i = 0; i < ySize; i++)
			badParketLine += parketValues[random.nextInt(parketValues.length)];
		return badParketLine;
	}

	public void setRandom(boolean isRandom) {
		this.isRandom = isRandom;
	}
	
	
}
