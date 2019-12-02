package org.wahlzeit.extension;

import java.util.Objects;

import org.wahlzeit.extension.Guard.InvalidDoubleValues;

/**
 * Describes an unambigius point in a cartesian coordinate system
 * 
 */
public class CartesianCoordinate extends AbstractCoordinate{
	
	private double x;
	private double y;
	private double z;
	
	/**
	 * Instanciates a cartesian coordinate and initializes x, y and z with 0.
	 * Resulting coordinate is equal to the origin of the coordinate system.
	 */
	public CartesianCoordinate(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	/**
	 * Instanciates a cartesian coordinate
	 * @param x non-infinite value
	 * @param y non-infinite value
	 * @param z non-infinite value
	 */
	public CartesianCoordinate(double x, double y, double z) {
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
	
	/**
	 * @methodtype set
	 */
	public void setX(double x) {
		Guard.assertDoubleArgumentValid(x, InvalidDoubleValues.NAN_INFINITY, "x");
		this.x = x;
	}
	
	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		Guard.assertDoubleArgumentValid(y, InvalidDoubleValues.NAN_INFINITY, "y");
		this.y = y;
	}
	
	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		Guard.assertDoubleArgumentValid(z, InvalidDoubleValues.NAN_INFINITY, "z");
		this.z = z;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		CartesianCoordinate coordinate = new CartesianCoordinate(getX(),getY(),getZ());
		return coordinate;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();
		CartesianCoordinate origin = new CartesianCoordinate();
		double radius = getCartesianDistance(origin);
		double theta = Math.acos(z/radius);
		double phi = Math.atan2(y,x);
		assertClassInvariants();
		return new SphericCoordinate(radius, theta, phi);
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
