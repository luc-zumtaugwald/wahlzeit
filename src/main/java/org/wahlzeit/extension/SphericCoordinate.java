package org.wahlzeit.extension;

import java.util.Objects;

/**
 * Describes an unambigious point in a spherical coordinate system
 */
public class SphericCoordinate extends AbstractCoordinate {

    private double radius;
    private double theta;
    private double phi;

    /**
     * Instanciates a new Spherical coordinate
     * and initializes all components with 0.
     * Resulting coordinate is equal to the origin of the coordinate system.
     */
    public SphericCoordinate(){
        radius = 0;
        theta = 0;
        phi = 0;
    }
    /**
     * Instanciates a new Spherical coordinate
     * @param radius value greater than 0
     * @param theta value between 0 and pi
     * @param phi value between -pi and +pi
     */
    public SphericCoordinate(double radius, double theta, double phi){
        Guard.assertArgumentGreaterThan(radius, 0, false, "radius");
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
     * Sets the radius
     * @methodType set
     * @param radius a value greater than 0
     */
    public void setRadius(double radius) {
        Guard.assertArgumentGreaterThan(radius, 0, false, "radius");
        this.radius = radius;
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
     * Sets theta
     * @methodType set
     * @param theta a value between 0 and pi
     */
    public void setTheta(double theta) {
        Guard.assertArgumentInRange(theta, 0d, Math.PI, "theta");
        this.theta = theta;
    }
    /**
     * Gets phi
     * @methodType get
     * @return phi (value between -pi and +pi)
     */
    public double getPhi() {
        return phi;
    }

    /**
     * Sets phi
     * @methodType set
     * @param phi a value between -pi and +pi
     */
    public void setPhi(double phi) {
        Guard.assertArgumentInRange(phi, -Math.PI, Math.PI, "phi");
        this.phi = phi;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        assertClassInvariants();
        return new CartesianCoordinate(x,y,z);
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {
        return new SphericCoordinate(radius,theta,phi);
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