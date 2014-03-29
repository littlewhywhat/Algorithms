package com.littlewhywhat.geometry;

import java.util.List;

import com.littlewhywhat.datastructure.divider.SimpleArrayDivider;

public class Geometry {
	public static boolean canBePlacedIntoCircleWithCenter(Figure figure,
			Point center) {
		Point first = figure.getVertice(0);
		double distance = computeDistanceBetweenPoints(first, center);
		for (int i = 1; i < figure.getPoints().length; i++)
			if (computeDistanceBetweenPoints(center, figure.getVertice(i)) != distance)
				return false;
		return true;
	}

	public static boolean checkIfPointIsInAngle(Point item, Point center,
			Point startPoint, Point endPoint) {
		Line one = new Line();
		one.setByPoints(center, startPoint);
		Line two = new Line();
		two.setByPoints(center, endPoint);
		return checkIfPointsAreAtTheSamePositionToLine(one, endPoint, item)
				&& checkIfPointsAreAtTheSamePositionToLine(two, startPoint,
						item);
	}

	public static boolean checkIfPointsAreAtTheSamePositionToLine(Line line,
			Point one, Point two) {
		double positionOne = line.getPosition(one);
		double positionTwo = line.getPosition(two);
		if (positionOne == 0 || positionTwo == 0)
			return true;
		return (line.getPosition(one) < 0) == (line.getPosition(two) < 0);
	}

	public static int computeAngleByPoints(Point one, Point two) {
		int divident = Math.abs(one.getY() - two.getY());
		int divider = Math.abs(one.getX() - two.getX());
		int result = divident / divider;
		return (int) Math.toDegrees(Math.atan(result));
	}

	public static Point computeCenterPointBetweenPoints(Point one, Point two) {
		int x = Math.abs((one.getX() + two.getX()) / 2);
		int y = Math.abs((one.getY() + two.getY()) / 2);
		return new Point(x, y);
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

	public static double computeDistanceBetweenPoints(Point one, Point two) {
		double powerOne = Math.pow(one.getX() - two.getX(), 2);
		double powerTwo = Math.pow(one.getY() - two.getY(), 2);
		return Math.sqrt(powerOne + powerTwo);
	}

	public static double computeSquare(List<Point> points) {
		if (points.size() > 2) {
			double ySum = 0;
			double xSum = 0;
			Point prevPoint = points.get(points.size() - 1);
			for (int i = 0; i < points.size(); i++) {
				Point nextPoint = points.get(i);
				xSum += prevPoint.getX() * nextPoint.getY();
				ySum += prevPoint.getY() * nextPoint.getX();
				prevPoint = nextPoint;
			}
			return Math.abs((xSum - ySum) / 2);
		}
		return 0;
	}

	public static double computeSquare(Point[] points) {
		if (points.length > 2) {
			double ySum = 0;
			double xSum = 0;
			Point prevPoint = points[points.length - 1];
			for (Point nextPoint : points) {
				xSum += prevPoint.getX() * nextPoint.getY();
				ySum += prevPoint.getY() * nextPoint.getX();
				prevPoint = nextPoint;
			}
			return Math.abs((xSum - ySum) / 2);
		}
		return 0;
	}

	public static double computeSquare(SimpleArrayDivider<Point> dataDivider,
			int part) {
		dataDivider.goToPart(part);
		Point first = dataDivider.getItem();
		Point prevPoint = first;
		Point nextPoint;
		double ySum = 0;
		double xSum = 0;
		while (dataDivider.partHasItems()) {
			nextPoint = dataDivider.getItem();
			xSum += prevPoint.getX() * nextPoint.getY();
			ySum += prevPoint.getY() * nextPoint.getX();
			prevPoint = nextPoint;
		}
		xSum += prevPoint.getX() * first.getY();
		ySum += prevPoint.getY() * first.getX();
		return Math.abs((xSum - ySum) / 2);
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

}
