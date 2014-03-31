package com.littlewhywhat.algorithms.badparket;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class ParketReader extends TextFileInputReader<int[], Data> {

	private static final char BAD_PARKET = '*';
	
	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		int xLength = scanner.nextInt();
		int yLength = scanner.nextInt();
		Data data = new Data(xLength, yLength);
		executeConfig(scanner);	
		String line;
		boolean isBad;
		for (int x = 0; x < xLength; x++) {
			line = scanner.nextLine();
			for (int y = 0; y < yLength; y++) {
				isBad = false;
				if (line.charAt(y) == BAD_PARKET)
					isBad = true;
				data.add(x, y, isBad);
			}
		}
		setData(data);
	}

	private void executeConfig(Scanner scanner) {
		int[] config = new int[2];
		config[0] = scanner.nextInt();
		config[1] = scanner.nextInt();
		this.setConfig(config);
		scanner.nextLine();
	}

}
