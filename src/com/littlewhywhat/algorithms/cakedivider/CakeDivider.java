package com.littlewhywhat.algorithms.cakedivider;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.datastructure.sieve.SerialArraySieve;
import com.littlewhywhat.geometry.Figure;
import com.littlewhywhat.geometry.FigureDosator;
import com.littlewhywhat.geometry.Geometry;
import com.littlewhywhat.geometry.Point;

public class CakeDivider extends AbstractAlgorithm<Void, Point[], String[]> {
	private static final int NUMBER_OF_PARTS = 4;
	private Point center;
	private int dividerAngle;
	private CakeDosator dosator;

	private void setWrongOutput() {
		setOutput(new String[] { "-1" });
	}

	private void setRightOutput() {
		setOutput(new String[] { center.getX() + " " + center.getY(),
				String.valueOf(dividerAngle) });
	}

	@Override
	public void execute() {
		dosator = new CakeDosator();
		if (checkPointsCount() && computeIfDividerExists()
				&& checkCakeSymmetry()) {
			setRightOutput();
		} else
			setWrongOutput();
	}

	private void computeAngle(Point vertice) {
		dividerAngle = Geometry.computeAngleByPoints(vertice, center);
	}

	private boolean computeIfDividerExists() {
		int count = 0;
		while (dosator.hasDose()) {
			dosator.nextDose();
			
			if (Geometry.isEqualSided(dosator.getDoseFigure())) {
				computeCenter(dosator.getDoseFigure());
				computeAngle(dosator.getDoseFigure().getVertice(0));
				System.out.println( "square " + count + "\n" +
						"first " + dosator.getDoseFigure().getPoints()[0] + "\n" + 
						"second " + dosator.getDoseFigure().getPoints()[1] + "\n" +
						"third " + dosator.getDoseFigure().getPoints()[2] + "\n" +
						"fourth " + dosator.getDoseFigure().getPoints()[3] + "\n" +
						"center " + center
						);
				return true;
			}
			count++;
		}
		return false;
	}

	private boolean checkCakeSymmetry() {
		dosator.reset();
		while (dosator.hasDose()) {
			dosator.nextDose();
			if (!Geometry.canBePlacedIntoCircleWithCenter(dosator.getDoseFigure(), center)) {
				System.out.println(  "can't be placed \n" +
						"first " + dosator.getDoseFigure().getPoints()[0] + "\n" + 
						"second " + dosator.getDoseFigure().getPoints()[1] + "\n" +
						"third " + dosator.getDoseFigure().getPoints()[2] + "\n" +
						"fourth " + dosator.getDoseFigure().getPoints()[3] + "\n" +
						"center " + center
						);
				return false;
			}
		}
		return true;
	}

	private void computeCenter(Figure figure) {
		center = Geometry.computeCircumCenter(figure.getVertice(0),
				figure.getVertice(1), figure.getVertice(2));
	}

	private boolean checkPointsCount() {
		return getData().length % NUMBER_OF_PARTS == 0;
	}

	private class CakeDosator extends FigureDosator {
		public CakeDosator() {
			super();
			setArraySieve(new SerialArraySieve<Point>());
			setArray(getData());
			setDoseFigure(NUMBER_OF_PARTS);
		}
	}
}
