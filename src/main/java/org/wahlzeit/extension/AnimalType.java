package org.wahlzeit.extension;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.googlecode.objectify.annotation.Entity;

import org.wahlzeit.services.DataObject;

/**
 * AnimalType
 */
public class AnimalType {

    protected AnimalType superType = null;
    protected Set<AnimalType> subTypes = new HashSet<AnimalType>();

    private String name;
    private int lifeExpectancy;
    
    public AnimalType(String name){
        this.name = name;
    }

    public AnimalType getSuperType() {
        return superType;
    }

    private void setSuperType(AnimalType superType) {
        this.superType = superType;
    }

    public Iterator<AnimalType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public void addSubType(AnimalType animalType) {
        Guard.assertArgumentNotNull(animalType, "animalType");
        animalType.setSuperType(this);
        subTypes.add(animalType);
    }

    public boolean isSubtype() {
        return superType != null;
    }

    public String getName() {
        return name;
    }

    public Animal createInstance(){
        return new Animal(this);
    }

    public boolean hasInstance(Animal animal) {
        Guard.assertArgumentNotNull(animal, "animal");

        if (animal.getType() == this) {
          return true;
        }
    
        for (AnimalType type : subTypes) {
          if (type.hasInstance(animal)) {
            return true;
          }
        }
    
        return false;
      }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
}