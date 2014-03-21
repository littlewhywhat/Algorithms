package com.littlewhywhat.geometry;

public class Figure {
	
	private Point[] points;

	@Override
	public Figure clone() {
		try {
			Figure result = (Figure) super.clone();
			result.points = points.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	
	public Point getVertice(int index) {
		return points[index];
	}
	
	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

}
