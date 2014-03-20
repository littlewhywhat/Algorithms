package com.littlewhywhat.algorithms.cakedivider;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.geometry.GeometryHelper;
import com.littlewhywhat.geometry.Point;

public class CakeDivider extends AbstractAlgorithm<Void, Point[], String[]> {
	private static final int NUMBER_OF_PARTS = 4;
	private Point center;
	private int angle;
	private int squareCorner = -1;
	private IndicesTracker tracker;

	private void setWrongOutput() {
		String[] answers = new String[1];
		answers[0] = "-1";
		setOutput(answers);
	}

	private void setRightOutput() {
		String[] answers = new String[2];
		answers[0] = center.getX() + " " + center.getY();
		answers[1] = String.valueOf(angle);
		setOutput(answers);
	}

	@Override
	public void execute() {
		if (checkConditions()) {
			computeAnswer();
			setRightOutput();
		} else
			setWrongOutput();
	}

	private void computeAnswer() {
		angle = GeometryHelper.computeAngleByPoints(getData()[squareCorner],
				center);
	}

	private boolean checkConditions() {
		return checkPointsCount() && checkIfSquareExists()
				&& checkOtherPointsSymmetry();
	}

	private boolean checkIfSquareExists() {
		tracker = new IndicesTracker(getData().length, NUMBER_OF_PARTS);
		int[] indices;
		while (tracker.hasNext()) {
			indices = tracker.goNext();
			if (GeometryHelper.checkFourPointsAreSquare(getPoint(indices[0]),
					getPoint(indices[1]), getPoint(indices[2]),
					getPoint(indices[3]))) {
				squareCorner = indices[0];
				computeCenter(getPoint(squareCorner),
						getPoint(indices[NUMBER_OF_PARTS / 2]));
				return true;
			}
		}
		return false;
	}

	private boolean checkOtherPointsSymmetry() {
		tracker.goToBeginning();
		while (tracker.hasNext()) {
			if (!checkDistanceToCenter(tracker.goNext()))
				return false;
		}
		return true;
	}

	private Point getPoint(int i) {
		return getData()[i];
	}

	private void computeCenter(Point one, Point two) {
		center = GeometryHelper.computeCenterPointBetweenPoints(one, two);
	}

	private boolean checkDistanceToCenter(int[] fourIndices) {
		double firstDistance = GeometryHelper.computeDistanceBetweenPoints(
				getData()[fourIndices[0]], center);
		return (firstDistance == GeometryHelper.computeDistanceBetweenPoints(
				getData()[fourIndices[1]], center)
				&& firstDistance == GeometryHelper
						.computeDistanceBetweenPoints(
								getData()[fourIndices[2]], center) && firstDistance == GeometryHelper
					.computeDistanceBetweenPoints(getData()[fourIndices[3]],
							center));
	}

	private boolean checkPointsCount() {
		return getData().length % NUMBER_OF_PARTS == 0;
	}

}
