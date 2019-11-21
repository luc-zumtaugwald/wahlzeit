package org.wahlzeit.extension;

import java.util.Objects;

/**
 * This class represents a cartesian coordinate
 * 
 */
public class CartesianCoordinate extends AbstractCoordinate{
	
	private double x;
	private double y;
	private double z;
	
	public CartesianCoordinate(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public CartesianCoordinate(double x, double y, double z) {
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
		this.x = x;
	}
	
	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * calculates the euclidian distance to a specific coordinate
	 */
	protected double getDistance(CartesianCoordinate coordinate) {
		if(coordinate == null) {
			throw new IllegalArgumentException("coordinate must not be null");
		}
		double dX = coordinate.getX() - this.getX();
		double dY = coordinate.getY() - this.getY();
		double dZ = coordinate.getZ() - this.getZ();
		return Math.sqrt(dX*dX+dY*dY+dZ*dZ);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		CartesianCoordinate coordinate = new CartesianCoordinate(getX(),getY(),getZ());
		return coordinate;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		CartesianCoordinate origin = new CartesianCoordinate();
		double radius = getDistance(origin);
		double theta = Math.acos(z/radius);
		double phi = Math.atan2(y,x);
		return new SphericCoordinate(radius, theta, phi);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
}
