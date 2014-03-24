package com.littlewhywhat.algorithms.cakedivider;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.datastructure.divider.SimpleArrayDivider;
import com.littlewhywhat.datastructure.sieve.SerialArraySieve;
import com.littlewhywhat.geometry.Figure;
import com.littlewhywhat.geometry.FigureDosator;
import com.littlewhywhat.geometry.Geometry;
import com.littlewhywhat.geometry.Point;

public class CakeDivider extends AbstractAlgorithm<Void, Point[], String[]> {
	private class CakeArrayDivider extends SimpleArrayDivider<Point> {
		public CakeArrayDivider() {
			super();
			setArray(getData());
			setNumberOfParts(NUMBER_OF_PARTS);
		}
	}
	private class CakeDosator extends FigureDosator {
		public CakeDosator() {
			super();
			setArraySieve(new SerialArraySieve<Point>());
			setArray(getData());
			setDoseFigure(NUMBER_OF_PARTS);
		}
	}
	private static final int NUMBER_OF_PARTS = 4;
	private Point center;
	private int dividerAngle;

	private CakeDosator dataDosator;

	private CakeArrayDivider dataDivider;

	private boolean checkEachPartPoints() {
		Point startPoint;
		Point endPoint;
		int prevIndex = NUMBER_OF_PARTS - 1;
		for (int nextIndex = 0; nextIndex < NUMBER_OF_PARTS; nextIndex++) {
			startPoint = dataDosator.getDoseFigure().getVertice(prevIndex);
			endPoint = dataDosator.getDoseFigure().getVertice(nextIndex);
			dataDivider.goToPart(prevIndex);
			while (dataDivider.partHasItems()) {
				Point item = dataDivider.getItem();
				if (!Geometry.checkIfPointIsInAngle(item, center, startPoint,
						endPoint))
					return false;
			}
			prevIndex = nextIndex;
		}
		return true;
	}

	private boolean checkEachPartSquares() {
		double firstPartSquare = Geometry.computeSquare(getPartPoints(0));
		double otherPartSquare;
		for (int part = 1; part < NUMBER_OF_PARTS; part++) {
			Point[] partPoints = getPartPoints(part);
			otherPartSquare = Geometry.computeSquare(partPoints);
			if (otherPartSquare != firstPartSquare)
				return false;
		}
		return true;
	}

	private boolean checkPointsCount() {
		return getData().length % NUMBER_OF_PARTS == 0;
	}

	private void computeAngle(Point vertice) {
		dividerAngle = Geometry.computeAngleByPoints(vertice, center);
	}

	private void computeCenter(Figure figure) {
		center = Geometry.computeCircumCenter(figure.getVertice(0),
				figure.getVertice(1), figure.getVertice(2));
	}

	private boolean computeIfDividerExists() {
		int count = -1;
		while (dataDosator.hasDose()) {
			count++;
			dataDosator.nextDose();
			Figure dose = dataDosator.getDoseFigure();
			if (!Geometry.isEqualSided(dose))
				continue;
			computeCenter(dose);
			computeAngle(dose.getVertice(0));
			dataDivider.setStartIndex(count);
			if (!checkEachPartPoints())
				continue;
			if (!checkEachPartSquares())
				continue;
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		dataDosator = new CakeDosator();
		dataDivider = new CakeArrayDivider();
		if (checkPointsCount() && computeIfDividerExists()) {
			setRightOutput();
		} else
			setWrongOutput();
	}

	private Point[] getPartPoints(int part) {
		int count = 0;
		Point[] partPoints = new Point[getData().length / NUMBER_OF_PARTS + 2];
		dataDivider.goToPart(part);
		while (dataDivider.partHasItems()) {
			partPoints[count] = dataDivider.getItem();
			count++;
		}
		partPoints[partPoints.length - 2] = dataDivider.getItem();
		partPoints[partPoints.length - 1] = center;
		return partPoints;
	}

	private void setRightOutput() {
		setOutput(new String[] { center.getX() + " " + center.getY(),
				String.valueOf(dividerAngle) });
	}

	private void setWrongOutput() {
		setOutput(new String[] { "-1" });
	}
}
