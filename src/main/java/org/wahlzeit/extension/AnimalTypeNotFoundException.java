package org.wahlzeit.extension;

/**
 * AnimalTypeNotFoundException
 */
public class AnimalTypeNotFoundException extends Throwable {

    String typeName;

    public String getTypeName() {
        return typeName;
    }

    public AnimalTypeNotFoundException(String typeName) {
        super("Der Typ '"+typeName+"' wurde nicht gefunden");
        this.typeName = typeName;
    }
    
}