package org.wahlzeit.model;
/**
 * This class represents a cartesian coordinate
 * 
 */
public class Coordinate {
	
	private double x;
	private double y;
	private double z;
	
	public Coordinate(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * calculates the euclidian distance to a specific coordinate
	 */
	protected double getDistance(Coordinate coordinate) {
		if(coordinate == null) {
			throw new IllegalArgumentException("coordinate must not be null");
		}
		double dX = coordinate.x - this.x;
		double dY = coordinate.y - this.y;
		double dZ = coordinate.z - this.z;
		return Math.sqrt(dX*dX+dY*dY+dZ*dZ);
	}
	
	protected boolean isEqual(Coordinate coordinate) {
		if(coordinate == null) {
			return false;
		}
		return compareDoubles(this.x, coordinate.x) && compareDoubles(this.y, coordinate.y) && compareDoubles(this.z, coordinate.z);
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
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Coordinate) && this.isEqual((Coordinate)obj);
	}
	
	@Override
	public int hashCode() {
		return (int)(this.x+this.y+this.z);
	}

	private static final double EPSILON = 1e-5;
	private boolean compareDoubles(double a, double b){
		return Math.abs(a-b) < EPSILON;
	}
}
