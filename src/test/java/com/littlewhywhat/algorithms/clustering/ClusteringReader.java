package com.littlewhywhat.algorithms.clustering;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class ClusteringReader extends TextFileInputReader<Integer, Map<BinaryString, Boolean>> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		final Map<BinaryString, Boolean> map = new HashMap<BinaryString, Boolean>();
		scanner.nextLine();
		while (scanner.hasNext()) {
			String string = scanner.nextLine();
			boolean[] node = new boolean[string.length()];
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == '1')
					node[i] = true;
			}
			map.put(new BinaryString(node), false);
		}
		setData(map);
	}

}
