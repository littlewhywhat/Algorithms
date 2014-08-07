package com.littlewhywhat.algorithms.salesman;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class CityReader extends TextFileInputReader<Void, List<City>> {

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		scanner.nextLine();
		final List<City> cities = new ArrayList<City>();
		while (scanner.hasNext())
			cities.add(getNewCity(scanner.nextDouble(), scanner.nextDouble()));
		setData(cities);
	}

	private City getNewCity(double x, double y) {
		return new SimpleCity(x,y);
	}

}
