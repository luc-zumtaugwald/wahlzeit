package org.wahlzeit.model;
/**
 * This class represents a cartesian coordinate
 * 
 */
public class CartesianCoordinate implements Coordinate{
	
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
	public boolean isEqual(Coordinate coordinate) {
		if(coordinate == null) {
			return false;
		}
		SphericCoordinate spheric = this.asSphericCoordinate();
		return spheric.isEqual(coordinate);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Coordinate) && this.isEqual((Coordinate)obj);
	}
	
	@Override
	public int hashCode() {
		return (int)(this.x+this.y+this.z);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		CartesianCoordinate coordinate = new CartesianCoordinate(getX(),getY(),getZ());
		return coordinate;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return getDistance(coordinate.asCartesianCoordinate());
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
	public double getCentralAngle(Coordinate coordinate) {
		if(coordinate == null){
			throw new IllegalArgumentException("coordinate must not be null");
		}
		SphericCoordinate spheric = coordinate.asSphericCoordinate();
		return spheric.getCentralAngle(coordinate);
	}

	
}
