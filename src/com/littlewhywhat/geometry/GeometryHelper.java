package com.littlewhywhat.geometry;

public class GeometryHelper {
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
		int result = divident/divider;		
		return (int) Math.toDegrees(Math.atan(result));
	}
}
