package com.littlewhywhat.algorithms.cakedivider;

import java.util.ArrayList;
import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.datastructure.Array;
import com.littlewhywhat.datastructure.collection.ArrayDivider;
import com.littlewhywhat.datastructure.sieve.SerialArraySieve;
import com.littlewhywhat.geometry.Figure;
import com.littlewhywhat.geometry.FigureDosator;
import com.littlewhywhat.geometry.Geometry;
import com.littlewhywhat.geometry.Point;

public class CakeDivider extends AbstractAlgorithm<Void, Point[], String[]> {

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

	private ArrayDivider<Point> dataDivider;

	private boolean checkEachPartPoints() {
		Point startPoint;
		Point endPoint;
		int prevIndex = NUMBER_OF_PARTS - 1;
		for (int nextIndex = 0; nextIndex < NUMBER_OF_PARTS; nextIndex++) {
			startPoint = dataDosator.getDoseFigure().getVertice(prevIndex);
			endPoint = dataDosator.getDoseFigure().getVertice(nextIndex);
			Array<Point> part = dataDivider.getPart(prevIndex);
			for (int i = 0; i < part.size(); i++) {
				if (!Geometry.checkIfPointIsInAngle(part.get(i), center,
						startPoint, endPoint))
					return false;
			}
			prevIndex = nextIndex;
		}
		return true;
	}

	private boolean checkEachPartSquares() {
		double firstPartSquare = Geometry.computeSquare(getAllPartPoints(0));
		double otherPartSquare;
		for (int part = 1; part < NUMBER_OF_PARTS; part++) {
			otherPartSquare = Geometry.computeSquare(getAllPartPoints(part));
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
		prepareData();
		if (checkPointsCount() && computeIfDividerExists()) {
			setRightOutput();
		} else
			setWrongOutput();
	}

	private void prepareData() {
		dataDosator = new CakeDosator();
		dataDivider = ArrayDivider.getInstance(getData(), NUMBER_OF_PARTS);	
	}

	private List<Point> getAllPartPoints(int part) {
		List<Point> allPoints = new ArrayList<Point>();
		Array<Point> partPoints = dataDivider.getPart(part);
		for (int i = 0; i < partPoints.size(); i++)
			allPoints.add(partPoints.get(i));
		allPoints.add(getNextPart(part).get(0));
		allPoints.add(center);
		return allPoints;
	}

	private Array<Point> getNextPart(int part) {
		if (part >= NUMBER_OF_PARTS)
			part -= NUMBER_OF_PARTS;
		return dataDivider.getPart(part);
	}

	private void setRightOutput() {
		setOutput(new String[] { center.getX() + " " + center.getY(),
				String.valueOf(dividerAngle) });
	}

	private void setWrongOutput() {
		setOutput(new String[] { "-1" });
	}
}
