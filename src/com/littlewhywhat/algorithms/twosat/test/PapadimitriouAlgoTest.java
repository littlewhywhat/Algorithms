package com.littlewhywhat.algorithms.twosat.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.twosat.ClauseCleaner;
import com.littlewhywhat.algorithms.twosat.ClauseReader;
import com.littlewhywhat.algorithms.twosat.PapadimitriouAlgo;

public class PapadimitriouAlgoTest {

	private final String FOLDER = "src/com/littlewhywhat/algorithms/twosat/test/input/";
	private final String INPUT = FOLDER + "2sat5.txt";
	private PapadimitriouAlgo algo;
	private ClauseCleaner cleaner;
	private ClauseReader reader;
	
	@Before
	public void setUp() throws Exception {
		algo = new PapadimitriouAlgo();
		reader = new ClauseReader();
		cleaner = new ClauseCleaner();
	}

	@Test
	public void test() {
		reader.setInputFilePath(INPUT);
		reader.read();
		System.out.println(reader.getData().size());
		cleaner.setConfig(reader.getConfig());
		cleaner.setData(reader.getData());
		cleaner.execute();
		System.out.println(cleaner.getOutput().size());
		//algo.setConfig(reader.getConfig());
		//algo.setData(cleaner.getData());
		//algo.execute();
		//System.out.println(algo.getOutput());
	}

}
