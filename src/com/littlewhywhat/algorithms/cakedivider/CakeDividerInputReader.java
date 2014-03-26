package com.littlewhywhat.algorithms.cakedivider;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;
import com.littlewhywhat.geometry.Point;

public class CakeDividerInputReader extends TextFileInputReader<Void, Point[]> {
	public final int MAX_VERTICES_NUMBER = 1500;
	public final int MIN_VERTICES_NUMBER = 3;

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		int defPointsCount = scanner.nextInt();
		if (defPointsCount >= MIN_VERTICES_NUMBER
				&& defPointsCount <= MAX_VERTICES_NUMBER)
			extractData(scanner, defPointsCount);
		else
			throw new InputMismatchException("Points count is not in right bounds");
	}

	private void extractData(Scanner scanner, int defPointsCount) {
		Point[] data = new Point[defPointsCount];
		try {
			for (int i = 0; i < defPointsCount; i++)
				data[i] = new Point(scanner.nextInt(), scanner.nextInt());
		} catch (NoSuchElementException e) {
			throw new InputMismatchException(e.getMessage());
		}
		setData(data);
	}

}
