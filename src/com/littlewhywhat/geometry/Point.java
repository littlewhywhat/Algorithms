package com.littlewhywhat.geometry;

public class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	protected Point clone() {		
		try {
			return (Point)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	
	
}
