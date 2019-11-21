package org.wahlzeit.extension;

/**
 * Coordinate
 */
public interface Coordinate {
    /***
     * Converts the Coordinate into a caresian Coordinate
     * @return cartesian coordinate
     */
    public CartesianCoordinate asCartesianCoordinate();
    /***
     * Converts the Coordinate into a spherical coordinate
     * @return spherical coordinate
     */
    public SphericCoordinate asSphericCoordinate();
    /**
     * Calculates the euclidian distance to another coordinate
     * @param coordinate 
     * @return
     */
    public double getCartesianDistance(Coordinate coordinate);  
    /**
     * Calcutes the central angle to another coordinate
     * @param coordinate
     * @return
     */
    public double getCentralAngle(Coordinate coordinate);
    /**
     * Determines if coordinate is equal to another coordinate
     * @param coordinate
     * @return
     */
    public boolean isEqual(Coordinate coordinate);
}