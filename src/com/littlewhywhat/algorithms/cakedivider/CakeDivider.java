package com.littlewhywhat.algorithms.cakedivider;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.geometry.Figure;
import com.littlewhywhat.geometry.FigureDosator;
import com.littlewhywhat.geometry.GeometryHelper;
import com.littlewhywhat.geometry.Point;

public class CakeDivider extends AbstractAlgorithm<Void, Point[], String[]> {
	private static final int NUMBER_OF_PARTS = 4;
	private Point center;
	private int dividerAngle;
	private FigureDosator dosator = new FigureDosator();

	private void setWrongOutput() {
		setOutput(new String[] { "-1" });
	}

	private void setRightOutput() {
		setOutput(new String[] { center.getX() + " " + center.getY(),
				String.valueOf(dividerAngle) });
	}

	@Override
	public void execute() {
		if (checkPointsCount() && computeIfDividerExists()
				&& checkCakeSymmetry()) {
			setRightOutput();
		} else
			setWrongOutput();
	}

	private void computeAngle(Point vertice) {
		dividerAngle = GeometryHelper.computeAngleByPoints(vertice, center);
	}

	private boolean computeIfDividerExists() {
		Figure figure = new Figure();
		figure.setPoints(new Point[NUMBER_OF_PARTS]);
		dosator.setDoseFigure(figure);
		while (dosator.hasDose()) {
			dosator.nextDose();
			if (GeometryHelper.isEqualSided(figure)) {
				computeCenter(figure);
				computeAngle(figure.getVertice(0));
				return true;
			}
		}
		return false;
	}

	private boolean checkCakeSymmetry() {
		Figure figure = new Figure();
		figure.setPoints(new Point[NUMBER_OF_PARTS]);
		dosator.setDoseFigure(figure);
		while (dosator.hasDose()) {
			dosator.nextDose();
			if (!GeometryHelper.canBePlacedIntoCircleWithCenter(figure, center))
				return false;
		}
		return true;
	}

	private void computeCenter(Figure figure) {
		center = GeometryHelper.computeCircumCenter(figure);
	}

	private boolean checkPointsCount() {
		return getData().length % NUMBER_OF_PARTS == 0;
	}

}
