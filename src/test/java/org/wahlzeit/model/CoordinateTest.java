package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {
	private final double delta = 1.0e-5;

	@Test
	public void testConstructorCartesian() {
		CartesianCoordinate coordinate = new CartesianCoordinate(20,30.5,50.9);
		assertEquals(20, coordinate.getX(), delta);
		assertEquals(30.5, coordinate.getY(), delta);
		assertEquals(50.9, coordinate.getZ(), delta);
	}
	
	@Test
	public void testDistanceCartesian() {
		Coordinate start = new CartesianCoordinate(3,6,7);
		Coordinate end = new CartesianCoordinate(10,13,25);
		double distance = start.getCartesianDistance(end);
		assertEquals(20.542639, distance , delta);
	}
	
	@Test
	public void testIsEqualCartesian() {
		Coordinate c1 = new CartesianCoordinate(34,25,12);
		Coordinate c2 = new CartesianCoordinate(34,25,12);
		assertTrue(c1.isEqual(c2));
		c2 = new CartesianCoordinate(35,26,12);
		assertFalse(c1.isEqual(c2));
		
		c2 = null;
		assertFalse(c1.isEqual(c2));
	}
	
	@Test
	public void testEqualsCartesian() {
		Coordinate c1 = new CartesianCoordinate(34.12345678,25.999999999999999,12);
		Coordinate c2 = new CartesianCoordinate(34.12345678,25.999999999999999,12);
		assertTrue(c1.equals(c2));
		c2 = new CartesianCoordinate(35,26,12);
		assertFalse(c1.equals(c2));
		
		c2 = null;
		assertFalse(c1.equals(c2));
		
		String notACoordinate = "Hi";
		assertFalse(c1.equals(notACoordinate));
	}

	@Test
	public void testEqualsSpheric() {
		Coordinate c1 = new SphericCoordinate(10,24,6);
		Coordinate c2 = new SphericCoordinate(10,24,6);
		assertTrue(c1.equals(c2));
		c2 = new SphericCoordinate(10.5,24,6);
		assertFalse(c1.equals(c2));
		
		c2 = null;
		assertFalse(c1.equals(c2));
		
		String notACoordinate = "Hi";
		assertFalse(c1.equals(notACoordinate));
	}

	@Test
	public void testCartesianToSphericCoordinate() {
		CartesianCoordinate c1 = new CartesianCoordinate(10,24,6);
		SphericCoordinate c2 = c1.asSphericCoordinate();
		assertEquals(c2.getRadius(), 26.683328128253, delta);
		assertEquals(c2.getPhi(), 1.1760052070951, delta);
		assertEquals(c2.getTheta(), 1.343997478741, delta);
		
	}
	@Test
	public void testSphericToCartesianCoordinate() {
		double delta = 1.0e-1;
		SphericCoordinate spheric = new SphericCoordinate(32.817678162844,1.0603080048781,1.0615239274196);
		CartesianCoordinate cartesian = spheric.asCartesianCoordinate();
		assertEquals(cartesian.getX(), 14.0, delta);
		assertEquals(cartesian.getY(), 25.0, delta);
		assertEquals(cartesian.getZ(), 16.0, delta);
		
	}
}
