package com.littlewhywhat.geometry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.datastructure.sieve.SerialArraySieve;

public class TestFigureDosator {

	FigureDosator dosator;

	@Before
	public void setUp() throws Exception {
		dosator = new FigureDosator();
		Point[] array = new Point[] { new Point(0, 0), new Point(1, 1),
				new Point(2, 2), new Point(3, 3), new Point(4, 4),
				new Point(5, 5) };
		dosator.setArraySieve(new SerialArraySieve<Point>());
		dosator.setArray(array);
		dosator.setDoseFigure(2);
	}

	@Test
	public void testNextDose() {
		dosator.nextDose();
		checkPoint(0, 0, dosator.getDoseFigure().getVertice(0));
		checkPoint(3, 3, dosator.getDoseFigure().getVertice(1));
		dosator.nextDose();
		checkPoint(1, 1, dosator.getDoseFigure().getVertice(0));
		checkPoint(4, 4, dosator.getDoseFigure().getVertice(1));
	}

	private void checkPoint(int x, int y, Point point) {
		Assert.assertEquals(x, point.getX());
		Assert.assertEquals(y, point.getX());
	}

	@Test
	public void testHasDose() {
		while (dosator.hasDose())
			dosator.nextDose();
		checkPoint(2, 2, dosator.getDoseFigure().getVertice(0));
		checkPoint(5, 5, dosator.getDoseFigure().getVertice(1));
	}

}
