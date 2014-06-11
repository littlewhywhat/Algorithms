package com.littlewhywhat.algorithms.graphs.search;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.io.AbstractSizeCounter;

public class SearchSizeCounter extends AbstractSizeCounter {

	@Override
	public void count(Scanner scanner) {
		while (scanner.hasNextInt()) {
			int verticeNumber = scanner.nextInt();
			if (verticeNumber > getSize())
				setSize(verticeNumber);
		}
	}


}
