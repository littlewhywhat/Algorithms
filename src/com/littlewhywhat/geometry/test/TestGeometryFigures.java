package com.littlewhywhat.geometry.test;

import com.littlewhywhat.geometry.Figure;
import com.littlewhywhat.geometry.Point;

public final class TestGeometryFigures {

	private static final Point[] SQUARE = new Point[] { new Point(0, 0),
			new Point(2, 0), new Point(2, 2), new Point(0, 2) };

	private static final Point[] STRAIGHT_TRIANGLE = new Point[] {
			new Point(0, 0), new Point(2, 0), new Point(2, 2) };

	public static Figure getSquare() {
		Figure figure = new Figure();
		figure.setPoints(SQUARE);
		return figure;
	}
	
	public static Figure getStraightTriangle() {
		Figure figure = new Figure();
		figure.setPoints(STRAIGHT_TRIANGLE);
		return figure;
	}
	
}
