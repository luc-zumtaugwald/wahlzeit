package org.wahlzeit.extension;

import java.util.Objects;

/**
 * AbstractCoordinate
 */
public abstract class AbstractCoordinate implements Coordinate {

    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if(coordinate == null){
            throw new IllegalArgumentException("coordinate must not be null");
        }
        CartesianCoordinate cartesian = this.asCartesianCoordinate();
        return cartesian.getDistance(coordinate.asCartesianCoordinate());
    }
    
    @Override
    public double getCentralAngle(Coordinate coordinate) {     
        if(coordinate == null){
            throw new IllegalArgumentException("coordinate must not be null");
        }
        SphericCoordinate c1 = this.asSphericCoordinate();
        SphericCoordinate c2 = coordinate.asSphericCoordinate();
        
		double deltaPhi = Math.abs(c1.getPhi()-c2.getPhi());
		
		return Math.acos(Math.sin(c1.getTheta())*Math.sin(c2.getTheta()) 
		+ Math.cos(c1.getTheta())*Math.cos(c2.getTheta())*Math.cos(deltaPhi));
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate == null) {
			return false;
        }
        CartesianCoordinate c1 = this.asCartesianCoordinate();
        CartesianCoordinate c2 = coordinate.asCartesianCoordinate();
        
		return compareDoubles(c1.getX(), c2.getX()) && compareDoubles(c1.getY(), c2.getY()) && compareDoubles(c1.getZ(), c2.getZ());
    }
    
    @Override
	public boolean equals(Object obj) {
		return (obj instanceof Coordinate) && this.isEqual((Coordinate)obj);
    }
    
	@Override
	public int hashCode() {	
        CartesianCoordinate cartesian = this.asCartesianCoordinate();
        return Objects.hash(cartesian.getX(), cartesian.getY(), cartesian.getZ());
    }
    
	private boolean compareDoubles(double a, double b){
        final double EPSILON = 1e-8;
		return Math.abs(a-b) < EPSILON;
    }
}