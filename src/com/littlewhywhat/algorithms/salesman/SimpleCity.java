package com.littlewhywhat.algorithms.salesman;

public class SimpleCity implements City {
	private double x;
	private double y;

	public SimpleCity(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getDistanceTo(City city) {
		SimpleCity simpleCity = (SimpleCity) city;
		return Math.sqrt(Math.pow(this.x - simpleCity.x, 2)
				+ Math.pow(this.y - simpleCity.y, 2));
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	
}
