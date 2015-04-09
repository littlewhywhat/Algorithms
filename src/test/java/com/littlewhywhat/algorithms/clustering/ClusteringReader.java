package com.littlewhywhat.algorithms.clustering;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class ClusteringReader extends TextFileInputReader<Integer, Data> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		final Map<BinaryString, BinaryString> map = new HashMap<BinaryString, BinaryString>();
		final List<BinaryString> list = new ArrayList<BinaryString>();
		scanner.nextLine();
		String stroke;
		boolean[] symbols;
		BinaryString string;
		while (scanner.hasNext()) {
			stroke = scanner.nextLine();
			symbols = new boolean[stroke.length()];
			for (int i = 0; i < stroke.length(); i++) {
				if (stroke.charAt(i) == '1')
					symbols[i] = true;
			}
			string = new BinaryString(symbols);
			map.put(string, string);
			list.add(string);
		}
		final Data data = new Data();
		data.setMap(map);
		data.setList(list);
		setData(data);
	}

}
