package com.littlewhywhat.geometry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLine {

	private Line parallelLine = new Line();
	private Line verticalLine = new Line();
	private Line minusKLine = new Line();
	private Line plusKLine = new Line();

	private Point center = new Point(1, 1);
	private Point verticalPoint = new Point(1, 2);
	private Point parallelPoint = new Point(2, 1);
	private Point minusKPointOne = new Point(3, 0);
	private Point minusKPointTwo = new Point(0, 3);
	private Point plusKPointOne = new Point(3, 0);
	private Point plusKPointTwo = new Point(6, 3);

	@Before
	public void setUp() throws Exception {
		parallelLine.setByPoints(center, parallelPoint);
		verticalLine.setByPoints(center, verticalPoint);
		minusKLine.setByPoints(minusKPointOne, minusKPointTwo);
		plusKLine.setByPoints(plusKPointOne, plusKPointTwo);
	}

	@Test
	public void testSetByPoints() {
		Assert.assertEquals(-1, verticalLine.getK(), 0);
		Assert.assertEquals(true, verticalLine.isVertical());
		Assert.assertEquals(0, parallelLine.getK(), 0);
		Assert.assertEquals(false, parallelLine.isVertical());
		Assert.assertEquals(true, plusKLine.getK() > 0);
		Assert.assertEquals(true, minusKLine.getK() < 0);
	}

	@Test
	public void testGetPosition() {
		checkPointsPosition(verticalLine, new Point(-1, 0), new Point(2,0));
		checkPointsPosition(parallelLine, new Point(0, 2), new Point(0,-1));
		checkPointsPosition(plusKLine, new Point(0, 1), new Point(0, -4));
		checkPointsPosition(minusKLine, new Point(-1, 0), new Point(4,0));
		
	}

	private void checkPointsPosition(Line line, Point prevPoint, Point nextPoint) {
		Point upPoint;
		Point underPoint;
		if (line.getK() < 0) {
			upPoint = nextPoint;
			underPoint = prevPoint;
		} else {
			upPoint = prevPoint;
			underPoint = nextPoint;
		}
		Assert.assertEquals(true, line.getPosition(upPoint) > 0);
		Assert.assertEquals(true, line.getPosition(underPoint) < 0);
		
	}

}
