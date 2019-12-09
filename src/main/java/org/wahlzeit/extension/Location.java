package org.wahlzeit.extension;

public class Location {
	protected final Coordinate coordinate;
	
	public Location(Coordinate coordinate) {
		Guard.assertArgumentNotNull(coordinate, "coordinate");
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	
}
