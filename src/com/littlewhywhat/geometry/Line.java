package com.littlewhywhat.geometry;

public class Line {
	private boolean isVertical = false;
	public boolean isVertical() {
		return isVertical;
	}

	private double k;
	private double m;

	public double getK() {
		return this.k;
	}

	public void setByPoints(Point one, Point two) {
		if (one.getX() == two.getX()) {
			this.k = -1;
			this.m = one.getX();
			if (!isVertical)
				isVertical = true;
		}
		else {
			this.k = ((double)one.getY() - two.getY())/((double)one.getX() - two.getX());
			this.m = one.getY() - one.getX()* k;
		}
	}

	public double getPosition(Point point) {
		double withoutY = -(point.getX()*k + m);
		return isVertical? withoutY : point.getY() + withoutY;
	}

}
