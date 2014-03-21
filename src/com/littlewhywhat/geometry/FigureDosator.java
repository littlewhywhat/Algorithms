package com.littlewhywhat.geometry;

import com.littlewhywhat.datastructure.sieve.SieveDosator;

public class FigureDosator extends SieveDosator<Point> {
	
	private Figure doseFigure = new Figure();
	
	public void setDoseFigure(int verticesNumber) {
		this.doseFigure = new Figure();
		this.doseFigure.setPoints(new Point[verticesNumber]);
		setDose(doseFigure.getPoints());
	}

	public Figure getDoseFigure() {
		return this.doseFigure;
	}
}
