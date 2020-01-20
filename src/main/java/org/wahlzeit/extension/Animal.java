package org.wahlzeit.extension;

import com.googlecode.objectify.annotation.Entity;

import org.wahlzeit.services.DataObject;

/**
 * Animal
 */
@Entity
public class Animal extends DataObject {
    private AnimalType type;
    private Location location;
    
    public Animal(AnimalType animalType){
        Guard.assertArgumentNotNull(animalType, "animalType");
        this.type = animalType;
    }

    public AnimalType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        Guard.assertArgumentNotNull(location, "location");
        this.location = location;
    }

}