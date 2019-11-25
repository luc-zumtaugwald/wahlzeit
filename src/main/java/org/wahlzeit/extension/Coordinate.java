package org.wahlzeit.extension;

/**
 * Coordinate
 */
public interface Coordinate {
    /***
     * Converts the Coordinate into a caresian Coordinate
     * @methodtype conversion
     * @return cartesian coordinate
     */
    public CartesianCoordinate asCartesianCoordinate();
    /***
     * Converts the Coordinate into a spherical coordinate
     * @methodtype conversion
     * @return spherical coordinate
     */
    public SphericCoordinate asSphericCoordinate();
    /**
     * Calculates the euclidian distance to another coordinate
     * @methodtype get
     * @param coordinate 
     * @return
     */
    public double getCartesianDistance(Coordinate coordinate);  
    /**
     * Calcutes the central angle to another coordinate
     * @methodtype get
     * @param coordinate
     * @return
     */
    public double getCentralAngle(Coordinate coordinate);
    /**
     * Determines if coordinate is equal to another coordinate
     * @methodtype boolean query
     * @param coordinate
     * @return true if coordinates are equal, false else.
     */
    public boolean isEqual(Coordinate coordinate);
}