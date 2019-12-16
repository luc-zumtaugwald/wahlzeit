package org.wahlzeit.extension;

import java.util.Objects;

/**
 * Describes an unambigious point in a spherical coordinate system
 */
public class SphericCoordinate extends AbstractCoordinate {

    private final double radius;
    private final double theta;
    private final double phi;

    /**
     * Gets a spheric coordinate
     * @param radius value greater than 0
     * @param theta value between 0 and pi
     * @param phi value between -pi and +pi
     * @return Spheric Coordinate
     */
    public static synchronized SphericCoordinate getSphericCoordinate(double radius, double theta, double phi){
        SphericCoordinate coordinate = new SphericCoordinate(radius, theta, phi);
        SphericCoordinate value = sphericCoordinateMap.get(coordinate);
        if (value != null) {
            return value;
        }
        sphericCoordinateMap.put(coordinate, coordinate);
        return coordinate;
    }
    /**
     * Gets a spheric coordinate with all components initialized with 0
     * @return origin of the coordinate system
     */
    public static SphericCoordinate getSphericCoordinate(){
		return getSphericCoordinate(0,0,0);
    }

    /**
     * Instanciates a new Spherical coordinate
     * @param radius value greater than 0
     * @param theta value between 0 and pi
     * @param phi value between -pi and +pi
     */
    private SphericCoordinate(double radius, double theta, double phi){
        Guard.assertArgumentGreaterOrEqualThan(radius, 0, false, "radius");
        Guard.assertArgumentInRange(theta, 0d, Math.PI, "theta");
        Guard.assertArgumentInRange(phi, -Math.PI, Math.PI, "phi");

        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }

    /**
     * Gets the radius
     * @methodType get
     * @return radius (value greater than 0)
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets Theta
     * @methodType get
     * @return theta (value between 0 and pi)
     */
    public double getTheta() {
        return theta;
    }
    /**
     * Gets phi
     * @methodType get
     * @return phi (value between -pi and +pi)
     */
    public double getPhi() {
        return phi;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        assertClassInvariants();
        return CartesianCoordinate.getCartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    protected void assertClassInvariants() {
        // radius valid?
        assert !Double.isNaN(radius) && !Double.isInfinite(radius) && radius >= 0;
        // theta valid?
        assert !Double.isNaN(theta) && theta >= 0 && theta <= Math.PI;
        // phi valid ?
        assert !Double.isNaN(phi) && phi > -Math.PI && phi <= Math.PI;
         
    }

}