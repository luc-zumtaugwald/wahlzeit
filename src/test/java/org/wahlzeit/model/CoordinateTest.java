package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {
	private final double delta = 0.01;

	@Test
	public void testConstructor() {
		Coordinate coordinate = new Coordinate(20,30.5,50.9);
		assertEquals(20, coordinate.getX(), delta);
		assertEquals(30.5, coordinate.getY(), delta);
		assertEquals(50.9, coordinate.getZ(), delta);
	}
	
	@Test
	public void testDistance() {
		Coordinate start = new Coordinate(3,6,7);
		Coordinate end = new Coordinate(10,13,25);
		assertEquals(20.542639, start.getDistance(end), delta);
	}
	
	@Test
	public void testIsEqual() {
		Coordinate c1 = new Coordinate(34,25,12);
		Coordinate c2 = new Coordinate(34,25,12);
		assertTrue(c1.isEqual(c2));
		c2 = new Coordinate(35,26,12);
		assertFalse(c1.isEqual(c2));
		
		c2 = null;
		assertFalse(c1.isEqual(c2));
	}
	
	@Test
	public void testEquals() {
		Coordinate c1 = new Coordinate(34.12345678,25.999999999999999,12);
		Coordinate c2 = new Coordinate(34.12345678,25.999999999999999,12);
		assertTrue(c1.equals(c2));
		c2 = new Coordinate(35,26,12);
		assertFalse(c1.equals(c2));
		
		c2 = null;
		assertFalse(c1.equals(c2));
		
		String notACoordinate = "Hi";
		assertFalse(c1.equals(notACoordinate));
	}
}
