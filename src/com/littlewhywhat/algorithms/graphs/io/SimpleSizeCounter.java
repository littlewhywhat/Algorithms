package com.littlewhywhat.algorithms.graphs.io;

import java.util.Scanner;

public class SimpleSizeCounter extends AbstractSizeCounter {

	@Override
	public void count(Scanner scanner) {
		while (scanner.hasNextLine()) {
			scanner.nextLine();
			incrementSize();
		}
	}

}
