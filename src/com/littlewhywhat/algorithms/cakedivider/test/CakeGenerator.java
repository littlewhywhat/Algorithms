package com.littlewhywhat.algorithms.cakedivider.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import com.littlewhywhat.algorithms.TextFileOutputWriter;
import com.littlewhywhat.datastructure.sieve.SerialArraySieve;
import com.littlewhywhat.geometry.Figure;
import com.littlewhywhat.geometry.FigureDosator;
import com.littlewhywhat.geometry.Geometry;
import com.littlewhywhat.geometry.Point;

public class CakeGenerator {
	private static Writer writer = new Writer();
	private static FigureDosator dosator = new FigureDosator();
	private static Random random = new Random();
	private static final int MAX_VALUE_X = 1000;
	private static final int MIN_VALUE_X = 750;
	private static final int MAX_VALUE_Y = 500;
	private static final int SQUARE_NUMBER_SIDES = 4;
	private static final int SQUARE_SIDE_LENGTH = 500;

	public static void writeCake(String fileOutputPath, Point[] cake) {
		writer.setOutput(cake);
		writer.setOutputFilePath(fileOutputPath);
		writer.write();
	}

	public static Point[] generateSquareCake(int numberOfVertices) {
		Point[] cake = new Point[numberOfVertices];
		for (int i = 0; i < cake.length; i++)
			cake[i] = new Point(0, 0);
		dosator.setArraySieve(new SerialArraySieve<Point>());
		dosator.setArray(cake);
		dosator.setDoseFigure(SQUARE_NUMBER_SIDES);
		Figure figure = null;
		while (dosator.hasDose()) {
			dosator.nextDose();
			figure = dosator.getDoseFigure();
			Point first = getRandomPoint();			
			changePointOfFigure(figure, 0, first.getX(), first.getY());
			changePointOfFigure(figure, 1, SQUARE_SIDE_LENGTH + first.getY(),
					-first.getX());
			changePointOfFigure(figure, 2, SQUARE_SIDE_LENGTH - first.getX(),
					-(SQUARE_SIDE_LENGTH + first.getY()));
			changePointOfFigure(figure, 3, -first.getY(), first.getX()
					- SQUARE_SIDE_LENGTH);
		}
		try {
			changePointOfFigure(figure, 0, SQUARE_SIDE_LENGTH, 0);
			changePointOfFigure(figure, 1, SQUARE_SIDE_LENGTH,
					-SQUARE_SIDE_LENGTH);
			changePointOfFigure(figure, 2, 0, -SQUARE_SIDE_LENGTH);
			changePointOfFigure(figure, 3, 0, 0);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return cake;
	}

	private static void changePointOfFigure(Figure figure, int index, int x,
			int y) {
		Point point = figure.getVertice(index);
		point.setX(x);
		point.setY(y);
	}

	private static Point getRandomPoint() {
		Point point;
		Point center = new Point(250, -250);
		Point startPoint = new Point (0,0);
		Point endPoint = new Point (0, 500);
		do {
			point = new Point(getRandomX(), getRandomY());
		}
		while (!Geometry.checkIfPointIsInAngle(point, center, startPoint,
						endPoint));
		return point;
	}

	private static int getRandomX() {
		return random.nextInt(MAX_VALUE_X) - MIN_VALUE_X;
	}

	private static int getRandomY() {
		return random.nextInt(MAX_VALUE_Y);
	}

	private static class Writer extends TextFileOutputWriter<Point[]> {
		@Override
		protected void writeOutputData(BufferedWriter bw) throws IOException {
			bw.write(String.valueOf(getOutput().length));
			bw.newLine();
			for (Point point : getOutput()) {
				bw.write(point.getX() + " " + point.getY());
				bw.newLine();
			}

		}

	}
}
