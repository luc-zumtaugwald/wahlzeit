package org.wahlzeit.extension;

import org.wahlzeit.extension.Guard.InvalidDoubleValues;

/**
 * Describes an unambigius point in a cartesian coordinate system
 * 
 */
public class CartesianCoordinate extends AbstractCoordinate{
	
	private final double x;
	private final double y;
	private final double z;
	
	/**
	 * Gets cartesian coordinate
 	 * @param x non-infinite value
	 * @param y non-infinite value
	 * @param z non-infinite value
	 * @return Cartesian coordinate
	 */
	public static synchronized CartesianCoordinate getCartesianCoordinate(double x, double y, double z){
		CartesianCoordinate coordinate = new CartesianCoordinate(x,y,z);
		CartesianCoordinate value = cartesianCoordinateMap.get(coordinate);
		if(value != null){
			return value;
		}
		cartesianCoordinateMap.put(coordinate, coordinate);
		return coordinate;
	}

	/**
	 * Gets cartesian coordinate with all components initialized with 0
	 * @return origin of the coordinate system
	 */
	public static CartesianCoordinate getCartesianCoordinate(){
		return getCartesianCoordinate(0,0,0);
	}

	/**
	 * Instanciates a cartesian coordinate
	 * @param x non-infinite value
	 * @param y non-infinite value
	 * @param z non-infinite value
	 */
	private CartesianCoordinate(double x, double y, double z) {
		Guard.assertDoubleArgumentValid(x, InvalidDoubleValues.NAN_INFINITY, "x");
		Guard.assertDoubleArgumentValid(y, InvalidDoubleValues.NAN_INFINITY, "y");
		Guard.assertDoubleArgumentValid(z, InvalidDoubleValues.NAN_INFINITY, "z");

		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		
		double radius = Math.sqrt(getX()*getX()+getY()*getY()+getZ()*getZ());
		double theta = Math.acos(z / radius);
		double phi = Math.atan2(y, x);
		assertClassInvariants();
		return SphericCoordinate.getSphericCoordinate(radius, theta, phi);
	}

	@Override
	protected void assertClassInvariants() {
		// x valid
		assert !Double.isNaN(x) && !Double.isInfinite(x);
		// y valid
		assert !Double.isNaN(y) && !Double.isInfinite(y);
		// z valid
		assert !Double.isNaN(z) && !Double.isInfinite(z);
	}
}
