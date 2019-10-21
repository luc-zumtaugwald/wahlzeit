package org.wahlzeit.model;

public class Coordinate {
	
	private double x;
	private double y;
	private double z;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getDistance(Coordinate coordinate) {
		double tempX = coordinate.x - this.x;
		double tempY = coordinate.y - this.y;
		double tempZ = coordinate.z - this.z;
		return Math.sqrt(tempX*tempX+tempY*tempY+tempZ*tempZ);
	}
	
	public boolean isEqual(Coordinate coordinate) {
		return coordinate.x==this.x && coordinate.y == this.y && coordinate.z == this.z;
	}
}
