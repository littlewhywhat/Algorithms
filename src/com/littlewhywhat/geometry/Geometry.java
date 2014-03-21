package com.littlewhywhat.geometry;

public class Geometry {
	public static double computeDistanceBetweenPoints(Point one, Point two) {
		double powerOne = Math.pow(one.getX() - two.getX(), 2);
		double powerTwo = Math.pow(one.getY() - two.getY(), 2);
		return Math.sqrt(powerOne + powerTwo);
	}

	public static Point computeCenterPointBetweenPoints(Point one, Point two) {
		int x = Math.abs((one.getX() + two.getX()) / 2);
		int y = Math.abs((one.getY() + two.getY()) / 2);
		return new Point(x, y);
	}

	public static int computeAngleByPoints(Point one, Point two) {
		int divident = Math.abs(one.getY() - two.getY());
		int divider = Math.abs(one.getX() - two.getX());
		int result = divident / divider;
		return (int) Math.toDegrees(Math.atan(result));
	}

	public static boolean isEqualSided(Figure figure) {
		Point first = figure.getVertice(0);
		Point last = figure.getVertice(figure.getPoints().length - 1);
		double side = computeDistanceBetweenPoints(first, last);
		for (int i = 1; i < figure.getPoints().length; i++) {
			if (computeDistanceBetweenPoints(figure.getVertice(i),
					figure.getVertice(i - 1)) != side)
				return false;
		}
		return true;
	}

	public static boolean canBePlacedIntoCircleWithCenter(Figure figure,
			Point center) {
		Point first = figure.getVertice(0);
		double distance = computeDistanceBetweenPoints(first, center);
		for (int i = 1; i < figure.getPoints().length; i++)
			if (computeDistanceBetweenPoints(center, figure.getVertice(i)) != distance)
				return false;
		return true;
	}

	public static Point computeCircumCenter(Point vertice1, Point vertice2,
			Point vertice3) {
		int A1 = vertice1.getX() - vertice2.getX();
		int B1 = vertice1.getY() - vertice2.getY();
		int C1 = A1 * (vertice1.getX() + vertice2.getX()) / 2 + B1
				* (vertice1.getY() + vertice2.getY()) / 2;
		int A2 = vertice2.getX() - vertice3.getX();
		int B2 = vertice2.getY() - vertice3.getY();
		int C2 = A2 * (vertice2.getX() + vertice3.getX()) / 2 + B2
				* (vertice2.getY() + vertice3.getY()) / 2;
		int x = (C1 * B2 - C2 * B1) / (A1 * B2 - A2 * B1);
		int y = (A1 * C2 - A2 * C1) / (A1 * B2 - A2 * B1);
		return new Point(x, y);
	}

}
