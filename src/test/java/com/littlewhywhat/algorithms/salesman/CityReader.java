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
		System.out.println("one");
		System.out.println(scanner.nextLine());
		System.out.println(scanner.hasNext());
		final List<City> cities = new ArrayList<City>();
		while (scanner.hasNext()) {
			Double one = scanner.nextDouble();
			Double two = scanner.nextDouble();
			System.out.println(one);
			System.out.println(two);
			cities.add(getNewCity(one, two));

		}
		setData(cities);
	}

	private City getNewCity(double x, double y) {
		return new SimpleCity(x,y);
	}

}
