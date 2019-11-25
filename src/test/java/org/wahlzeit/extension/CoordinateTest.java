package org.wahlzeit.extension;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.extension.CartesianCoordinate;
import org.wahlzeit.extension.Coordinate;
import org.wahlzeit.extension.SphericCoordinate;

public class CoordinateTest {
	private final double delta = 1.0e-15;
	
	private Coordinate sphericalA;
	private Coordinate sphericalB;
	private Coordinate cartesianA;
	private Coordinate cartesianB;

	@Before
	public void setupCoordinates(){
		sphericalA = new SphericCoordinate(7.681145747868608, 0.861968285336736, 0.540419500270584);
		sphericalB = new SphericCoordinate(5.385164807134504, 0.733581323640083, 0.982793723247329);
		//sphericalA should be equal to cartesianA (same with B)
		cartesianA = new CartesianCoordinate(5, 3, 5);
		cartesianB = new CartesianCoordinate(2, 3, 4);
	}

	//Constructors
	@Test
	public void testConstructorCartesian() {
		CartesianCoordinate coordinate = new CartesianCoordinate(20,30.5,50.9);
		assertEquals(20, coordinate.getX(), delta);
		assertEquals(30.5, coordinate.getY(), delta);
		assertEquals(50.9, coordinate.getZ(), delta);
	}

	@Test
	public void testConstructorSpherical() {
		SphericCoordinate coordinate = new SphericCoordinate(20,30.5,50.9);
		assertEquals(20, coordinate.getRadius(), delta);
		assertEquals(30.5, coordinate.getTheta(), delta);
		assertEquals(50.9, coordinate.getPhi(), delta);
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
	//isEqual
	@Test
	public void testSphericalCartesianIsEqual() {
		assertTrue(sphericalA.isEqual(cartesianA));
		assertTrue(cartesianB.isEqual(sphericalB));
	}

	@Test
	public void testSphericalCartesianIsNotEqual() {
		assertFalse(sphericalA.isEqual(cartesianB));
		assertFalse(cartesianA.isEqual(sphericalB));
	}

	@Test
	public void testHashCode(){
		assertEquals(sphericalA.hashCode(), cartesianA.hashCode());
		assertEquals(sphericalB.hashCode(), cartesianB.hashCode());
	}

	

	//conversion
	@Test
	public void testCartesianToSphericCoordinate() {
		Coordinate spherical = cartesianA.asSphericCoordinate();
		assertTrue(spherical.isEqual(sphericalA));	
		
	}
	@Test
	public void testSphericToCartesianCoordinate() {

		Coordinate cartesian = sphericalA.asCartesianCoordinate();
		assertTrue(cartesian.isEqual(cartesianA));
	}

	//calculations
	@Test
	public void testCartesianDistance() {
		final double expectedDistance = 20.54263858417414;

		Coordinate start = new CartesianCoordinate(3,6,7);
		Coordinate end = new CartesianCoordinate(10,13,25);
		double distance = start.getCartesianDistance(end);
		assertEquals(expectedDistance, distance , delta);

		end = end.asSphericCoordinate();
		distance = start.getCartesianDistance(end);
		assertEquals(expectedDistance, distance , delta);

		start = start.asSphericCoordinate();
		distance = start.getCartesianDistance(end);
		assertEquals(expectedDistance, distance , delta);
	}

	@Test
	public void testCentralAngle() {
		final double expectedAngle = 0.332509923359474;

		double angle = sphericalA.getCentralAngle(sphericalB);
		assertEquals(expectedAngle, angle , delta);

		angle = cartesianA.getCentralAngle(sphericalB);
		assertEquals(expectedAngle, angle , delta);

		angle = cartesianA.getCentralAngle(cartesianB);
		assertEquals(expectedAngle, angle , delta);
	}
}
