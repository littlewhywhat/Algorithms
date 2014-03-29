package com.littlewhywhat.geometry.test;

import org.junit.Assert;
import org.junit.Test;

import com.littlewhywhat.datastructure.divider.SimpleArrayDivider;
import com.littlewhywhat.geometry.Figure;
import com.littlewhywhat.geometry.Geometry;
import com.littlewhywhat.geometry.Line;
import com.littlewhywhat.geometry.Point;

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

	@Test
	public void testComputeSquare() {			
		
		Point[] array = new Point[] {
				new Point(0,0),
				new Point(2,0),
				new Point(2,2),
				new Point(0,2),
				new Point(0,0),
				new Point(2,0),
				new Point(2,2)
		};
		SimpleArrayDivider<Point> dataDivider = new SimpleArrayDivider<Point>();
		dataDivider.setArray(array);
		dataDivider.setNumberOfParts(2);
		dataDivider.setStartIndex(0);
		
		Assert.assertEquals(4, Geometry.computeSquare(dataDivider, 0), 0);
		Assert.assertEquals(2, Geometry.computeSquare(dataDivider, 1), 0);
	}
	
	@Test
	public void testComputeSquareArray() {
		Assert.assertEquals(4, Geometry.computeSquare(TestGeometryFigures.getSquare().getPoints()),0);
		Assert.assertEquals(2, Geometry.computeSquare(TestGeometryFigures.getStraightTriangle().getPoints()),0);
	}
	
	@Test
	public void testSamePositionToLineTrue() {
		Line line = new Line();
		line.setByPoints(new Point(1,0), new Point(2,1));
		Point one = new Point(0,0);
		Point two = new Point(0,1);		
		Assert.assertEquals(true, Geometry.checkIfPointsAreAtTheSamePositionToLine(line, one, two));
	}
	
	@Test
	public void testSamePositionToLineFalse() {
		Line line = new Line();
		line.setByPoints(new Point(1,0), new Point(2,1));
		Point one = new Point(0,0);
		Point two = new Point(2,0);		
		Assert.assertEquals(false, Geometry.checkIfPointsAreAtTheSamePositionToLine(line, one, two));
	}
	
	@Test
	public void testSamePositionToLineOnePointIsOnLine() {
		Line line = new Line();
		line.setByPoints(new Point(1,0), new Point(2,1));
		Point one = new Point(1,0);
		Point two = new Point(1,1);		
		Assert.assertEquals(true, Geometry.checkIfPointsAreAtTheSamePositionToLine(line, one, two));
	}
	
	@Test
	public void testCheckIfPointIsInAngleTrue() {
		Point item = new Point(3,2);
		Point center = new Point(3,1);
		Point startPoint = new Point(2,2);
		Point endPoint = new Point(4,2);	
		Assert.assertEquals(true, Geometry.checkIfPointIsInAngle(item, center, startPoint, endPoint));
	}
	
	@Test
	public void testCheckIfPointIsInAngleTruePointOnLine() {
		Point item = new Point(3,1);
		Point center = new Point(3,1);
		Point startPoint = new Point(2,2);
		Point endPoint = new Point(4,2);	
		Assert.assertEquals(true, Geometry.checkIfPointIsInAngle(item, center, startPoint, endPoint));
	}
	
	@Test
	public void testCheckIfPointIsInAngleTruePointOnLineTwo() {
		Point item = new Point(0,2);
		Point center = new Point(1,1);
		Point startPoint = new Point(0,2);
		Point endPoint = new Point(0,0);	
		Assert.assertEquals(true, Geometry.checkIfPointIsInAngle(item, center, startPoint, endPoint));
	}
	
	@Test
	public void testCheckIfPointIsInAngleTruePointOnLineThree() {
		Point item = new Point(-465, -500);
		Point center = new Point(250, -250);
		Point startPoint = new Point(-465, -500);
		Point endPoint = new Point(0,465);	
		Assert.assertEquals(true, Geometry.checkIfPointIsInAngle(item, center, startPoint, endPoint));
	}
	
	
	@Test
	public void testCheckIfPointIsInAngleFalseOne() {
		Point item = new Point(3,0);
		Point center = new Point(3,1);
		Point startPoint = new Point(2,2);
		Point endPoint = new Point(4,2);	
		Assert.assertEquals(false, Geometry.checkIfPointIsInAngle(item, center, startPoint, endPoint));
	}
	
	@Test
	public void testCheckIfPointIsInAngleFalseTwo() {
		Point item = new Point(2,1);
		Point center = new Point(3,1);
		Point startPoint = new Point(2,2);
		Point endPoint = new Point(4,2);	
		Assert.assertEquals(false, Geometry.checkIfPointIsInAngle(item, center, startPoint, endPoint));
	}
	
	@Test
	public void testCheckIfPointIsInAngleFalseThree() {
		Point item = new Point(4,1);
		Point center = new Point(3,1);
		Point startPoint = new Point(2,2);
		Point endPoint = new Point(4,2);	
		Assert.assertEquals(false, Geometry.checkIfPointIsInAngle(item, center, startPoint, endPoint));
	}
}
