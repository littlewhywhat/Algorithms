package com.littlewhywhat.geometry;

import com.littlewhywhat.datastructure.sieve.SerialArraySieve;
import com.littlewhywhat.datastructure.sieve.SieveDosator;

public class FigureDosator extends SieveDosator<Point> {
	
	public void setDoseFigure(Figure figure) {
		SerialArraySieve<Point> sieve = new SerialArraySieve<Point>();
		sieve.setArray(figure.getPoints());
		setArraySieve(sieve);
		setDose(figure.getPoints());
	}

}
