package com.littlewhywhat.geometry;

import org.junit.Assert;
import org.junit.Test;

public class TestGeometry {

	@Test
	public void testComputeDistanceBetweenPoints() {
		Point one = new Point(0,0);
		Point two = new Point(1,0);
		Assert.assertEquals(1, Geometry.computeDistanceBetweenPoints(one, two), 0);
	}
	
	@Test
	public void testComputeCenterPointBetweenPoints() {
		Point one = new Point(1,1);
		Point two = new Point(3,3);
		Point result = Geometry.computeCenterPointBetweenPoints(one, two);
		Assert.assertEquals(2, result.getX(), 0);
		Assert.assertEquals(2, result.getY(), 0);
	}
	
	@Test
	public void testComputeAngleByPoints() {
		Point one = new Point(0,0);
		Point two = new Point(2,2);
		Assert.assertEquals(45, Geometry.computeAngleByPoints(one, two));
	}
	
	@Test
	public void testIsEqualSidedTrue() {
		Figure figure = new Figure();
		Point[] square = new Point[] { 
				new Point(0,0),
				new Point(2,0),
				new Point(2,2),
				new Point(0,2)				
		};
		figure.setPoints(square);
		Assert.assertEquals(true, Geometry.isEqualSided(figure));
	}
	
	@Test
	public void testIsEqualSidedFalse() {
		Figure figure = new Figure();
		Point[] square = new Point[] { 
				new Point(0,0),
				new Point(2,0),
				new Point(2,2)			
		};
		figure.setPoints(square);
		Assert.assertEquals(false, Geometry.isEqualSided(figure));
	}

}
