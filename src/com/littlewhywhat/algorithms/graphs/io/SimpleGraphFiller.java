package com.littlewhywhat.algorithms.graphs.io;

import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;

public class SimpleGraphFiller implements GraphFiller {

	@Override
	public void fill(Graph graph, Scanner scanner) {
		while (scanner.hasNextLine()) {
			Scanner line = new Scanner(scanner.nextLine());
			int one = line.nextInt();
			while (line.hasNext()) {
				graph.connect(one, line.nextInt());
			}
			line.close();
		}
	}

}
