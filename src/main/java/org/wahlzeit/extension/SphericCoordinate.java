package org.wahlzeit.extension;

import java.util.Objects;

/**
 * SphericCoordinate
 */
public class SphericCoordinate extends AbstractCoordinate {

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
    public SphericCoordinate asSphericCoordinate() {
        return new SphericCoordinate(radius,theta,phi);
    }

}