package com.littlewhywhat.geometry;

import org.junit.Assert;
import org.junit.Test;

public class TestGeometry {

	@Test
	public void testComputeDistanceBetweenPoints() {
		Point one = new Point(0, 0);
		Point two = new Point(1, 0);
		Assert.assertEquals(1, Geometry.computeDistanceBetweenPoints(one, two),
				0);
	}

	@Test
	public void testComputeCenterPointBetweenPoints() {
		Point one = new Point(1, 1);
		Point two = new Point(3, 3);
		Point result = Geometry.computeCenterPointBetweenPoints(one, two);
		Assert.assertEquals(2, result.getX(), 0);
		Assert.assertEquals(2, result.getY(), 0);
	}

	@Test
	public void testComputeAngleByPoints() {
		Point one = new Point(0, 0);
		Point two = new Point(2, 2);
		Assert.assertEquals(45, Geometry.computeAngleByPoints(one, two));
	}

	@Test
	public void testIsEqualSidedTrue() {
		Assert.assertEquals(true,
				Geometry.isEqualSided(TestGeometryFigures.getSquare()));
	}

	@Test
	public void testIsEqualSidedFalse() {
		Assert.assertEquals(false, Geometry.isEqualSided(TestGeometryFigures
				.getStraightTriangle()));
	}

	@Test
	public void testCanBePlacedIntoCircleWithCenterTrue() {
		Assert.assertEquals(
				true,
				Geometry.canBePlacedIntoCircleWithCenter(
						TestGeometryFigures.getSquare(), new Point(1, 1)));

	}

	@Test
	public void testCanBePlacedIntoCircleWithCenterFalse() {
		Assert.assertEquals(false, Geometry.canBePlacedIntoCircleWithCenter(
				TestGeometryFigures.getStraightTriangle(), new Point(2, 2)));

	}

	@Test
	public void testCompute() {
		Figure triangle = TestGeometryFigures.getStraightTriangle();
		Point center = Geometry.computeCircumCenter(triangle.getVertice(0),
				triangle.getVertice(1), triangle.getVertice(2));
		
		Assert.assertEquals(1, center.getX());
		Assert.assertEquals(1, center.getY());
	}

}
