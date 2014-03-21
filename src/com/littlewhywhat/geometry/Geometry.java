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

	public static boolean fourPointsAreSquare(Point one, Point two,
			Point three, Point four) {
		double firstSide = computeDistanceBetweenPoints(one, two);
		return (firstSide == computeDistanceBetweenPoints(two, three)
				&& firstSide == computeDistanceBetweenPoints(three, four) 
				&& firstSide == computeDistanceBetweenPoints(four, one));
	}

	public static boolean isEqualSided(Figure figure) {
		Point first = figure.getVertice(0);
		Point last = figure.getVertice(figure.getPoints().length - 1);
		double side = computeDistanceBetweenPoints(first, last);
		for (int i = 1; i < figure.getPoints().length; i++) {
			if (computeDistanceBetweenPoints(figure.getVertice(i), figure.getVertice(i-1)) != side)
				return false;
		}			
		return true;
	}

	public static boolean canBePlacedIntoCircleWithCenter(Figure figure,
			Point center) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Point computeCircumCenter(Figure figure) {
		return null;
	}
	
}
