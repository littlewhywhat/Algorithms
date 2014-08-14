package com.littlewhywhat.algorithms.twosat.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.twosat.ClauseReader;
import com.littlewhywhat.algorithms.twosat.PapadimitriouAlgo;

public class PapadimitriouAlgoTest {

	private final String FOLDER = "src/com/littlewhywhat/algorithms/twosat/test/input/";
	private final String INPUT = FOLDER + "2sat1.txt";
	private PapadimitriouAlgo algo;
	private ClauseReader reader;
	
	@Before
	public void setUp() throws Exception {
		algo = new PapadimitriouAlgo();
		reader = new ClauseReader();
	}

	@Test
	public void test() {
		reader.setInputFilePath(INPUT);
		reader.read();
		algo.setData(reader.getData());
		System.out.println(algo.getOutput());
	}

}