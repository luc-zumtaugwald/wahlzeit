package org.wahlzeit.extension;

import java.util.HashMap;
import java.util.Objects;

/**
 * AbstractCoordinate
 */
@PatternInstance(
	patternName = "Template Method",
	participants = {
		"AbstractClass"
	}
)
public abstract class AbstractCoordinate implements Coordinate {
    
    protected static HashMap<Coordinate, CartesianCoordinate> cartesianCoordinateMap = new HashMap<>();
    protected static HashMap<Coordinate, SphericCoordinate> sphericCoordinateMap = new HashMap<>();


    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();


    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        Guard.assertArgumentNotNull(coordinate, "coordinate");

        CartesianCoordinate a = this.asCartesianCoordinate();
        CartesianCoordinate b = coordinate.asCartesianCoordinate();
        double distance = doGetCartesianDistance(a, b);

        assertClassInvariants();
        return distance;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        Guard.assertArgumentNotNull(coordinate, "coordinate");

        SphericCoordinate a = this.asSphericCoordinate();
        SphericCoordinate b = coordinate.asSphericCoordinate();
        double angle = doGetCentralAngle(a, b);

        assertClassInvariants();
        return angle;
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

	private double doGetCartesianDistance(CartesianCoordinate a, CartesianCoordinate b) {
        //Pythagorean theorem
		double dX = a.getX() - b.getX();
		double dY = a.getY() - b.getY();
		double dZ = a.getZ() - b.getZ();

		return Math.sqrt(dX*dX+dY*dY+dZ*dZ);
    }
    
    @Override
    public boolean isEqual(Coordinate coordinate) {

        if (coordinate == null) {
            return false;
        }

        CartesianCoordinate c1 = this.asCartesianCoordinate();
        CartesianCoordinate c2 = coordinate.asCartesianCoordinate();
        boolean isEqual = DoubleUtils.compareDoubles(c1.getX(), c2.getX()) 
            && DoubleUtils.compareDoubles(c1.getY(), c2.getY())
            && DoubleUtils.compareDoubles(c1.getZ(), c2.getZ());
        
        assertClassInvariants();
        return isEqual;
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
        assertClassInvariants();
        return Objects.hash(x,y,z);
    }
   /**
    * Asserts that all class invariants are fullfilled
    */
    protected abstract void assertClassInvariants();

  
}