package org.wahlzeit.model;

/**
 * SphericCoordinate
 */
public class SphericCoordinate implements Coordinate {

    private double radius;
    private double theta;
    private double phi;

    public SphericCoordinate(){
        radius = 0;
        theta = 0;
        phi = 0;
    }
    public SphericCoordinate(double radius, double theta, double phi){
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        return new CartesianCoordinate(x,y,z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if(coordinate == null){
            throw new IllegalArgumentException("coordinate must not be null");
        }
        CartesianCoordinate cartesian = this.asCartesianCoordinate();
        return cartesian.getCartesianDistance(coordinate);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return new SphericCoordinate(radius,theta,phi);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if(coordinate == null){
            throw new IllegalArgumentException("coordinate must not be null");
        }
		SphericCoordinate spheric = coordinate.asSphericCoordinate();
		double deltaPhi = Math.abs(phi-spheric.getPhi());
		
		return Math.acos(Math.sin(theta)*Math.sin(spheric.getTheta()) 
		+ Math.cos(theta)*Math.cos(spheric.getTheta())*Math.cos(deltaPhi));
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate == null) {
			return false;
		}
		SphericCoordinate spheric = coordinate.asSphericCoordinate();
		return compareDoubles(this.radius, spheric.getRadius()) && compareDoubles(this.theta, spheric.getTheta()) && compareDoubles(this.phi, spheric.getPhi());
    }

    private static final double EPSILON = 1e-15;
	private boolean compareDoubles(double a, double b){
		return Math.abs(a-b) < EPSILON;
    }
    
    @Override
	public boolean equals(Object obj) {
		return (obj instanceof Coordinate) && this.isEqual((Coordinate)obj);
	}
	
	@Override
	public int hashCode() {
		return (int)(this.theta+this.phi+this.radius);
	}
}