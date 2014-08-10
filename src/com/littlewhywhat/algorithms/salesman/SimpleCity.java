package com.littlewhywhat.algorithms.salesman;

public class SimpleCity implements City {
	private static int SIMPLECITY_COUNTER = 0;
	
	private double x;
	private double y;
	private int index;
	
	public SimpleCity(double x, double y) {
		this.x = x;
		this.y = y;
		this.index = SIMPLECITY_COUNTER++;
	}

	@Override
	public double getDistanceTo(City city) {
		SimpleCity simpleCity = (SimpleCity) city;
		return Math.sqrt(Math.pow(this.x - simpleCity.x, 2)
				+ Math.pow(this.y - simpleCity.y, 2));
	}

	@Override
	public String toString() {
		return index + "[" + x + ", " + y + "]";
	}

	@Override
	public int getIndex() {
		return index;
	}

	
}
