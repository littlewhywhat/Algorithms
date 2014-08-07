package com.littlewhywhat.algorithms.salesman.test;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.salesman.CityReader;
import com.littlewhywhat.algorithms.salesman.SalesmanAlgo;

public class TestSalesmanAlgo {

	private static final String INPUT_SMALL = "src/com/littlewhywhat/algorithms/salesman/test/input/tspSmall.txt";
	private CityReader reader;
	private SalesmanAlgo algo;
	
	@Before
	public void setUp() throws Exception {
		reader = new CityReader();
		algo = new SalesmanAlgo();
	}

	@Test
	public void test() {
		reader.setInputFilePath(INPUT_SMALL);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
	}

}
