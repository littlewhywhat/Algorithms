package com.littlewhywhat.geometry;

import org.junit.Assert;
import org.junit.Test;

public class TestGeometryHelper {

	@Test
	public void testComputeDistanceBetweenPoints() {
		Point one = new Point(0,0);
		Point two = new Point(1,0);
		Assert.assertEquals(1, GeometryHelper.computeDistanceBetweenPoints(one, two), 0);
	}
	
	@Test
	public void testComputeCenterPointBetweenPoints() {
		Point one = new Point(1,1);
		Point two = new Point(3,3);
		Point result = GeometryHelper.computeCenterPointBetweenPoints(one, two);
		Assert.assertEquals(2, result.getX(), 0);
		Assert.assertEquals(2, result.getY(), 0);
	}
	
	@Test
	public void testComputeAngleByPoints() {
		Point one = new Point(0,0);
		Point two = new Point(2,2);
		Assert.assertEquals(45, GeometryHelper.computeAngleByPoints(one, two));
	}

}
