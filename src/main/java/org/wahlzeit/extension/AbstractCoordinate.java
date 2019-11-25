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
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
        CartesianCoordinate cartesian = this.asCartesianCoordinate();
        return cartesian.getDistance(coordinate.asCartesianCoordinate());
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
        SphericCoordinate a = this.asSphericCoordinate();
        SphericCoordinate b = coordinate.asSphericCoordinate();

        return doGetCentralAngle(a, b);
    }

    private double doGetCentralAngle(SphericCoordinate a, SphericCoordinate b){
        //Vincenty formula
        double deltaPhi = Math.abs(a.getPhi() - b.getPhi());
        double t1 = a.getTheta();
        double t2 = b.getTheta();

        double numerator = Math.sqrt(Math.pow(Math.cos(t2)*Math.sin(deltaPhi),2) 
            + Math.pow(Math.cos(t1)*Math.sin(t2) -Math.sin(t1)*Math.cos(t2)*Math.cos(deltaPhi),2));
        double denominator = Math.sin(t1)*Math.sin(t2) + Math.cos(t1)*Math.cos(t2)*Math.cos(deltaPhi);

        return Math.atan(numerator/denominator);
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        CartesianCoordinate c1 = this.asCartesianCoordinate();
        CartesianCoordinate c2 = coordinate.asCartesianCoordinate();

        return DoubleUtils.compareDoubles(c1.getX(), c2.getX()) 
            && DoubleUtils.compareDoubles(c1.getY(), c2.getY())
            && DoubleUtils.compareDoubles(c1.getZ(), c2.getZ());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Coordinate) && this.isEqual((Coordinate) obj);
    }

    @Override
    public int hashCode() {
        // rounding ensures that if a.equals(b) => a.hashCode() == b.hashCode()
        CartesianCoordinate cartesian = this.asCartesianCoordinate();
        double x = DoubleUtils.getRoundedValue(cartesian.getX());
        double y = DoubleUtils.getRoundedValue(cartesian.getY());
        double z = DoubleUtils.getRoundedValue(cartesian.getZ());
        return Objects.hash(x,y,z);
    }

    
}