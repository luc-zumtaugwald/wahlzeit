package org.wahlzeit.extension;

import java.util.HashMap;
import java.util.Map;


/**
 * AnimalManager
 */
public class AnimalManager {

    public static final String ROOT_TYPE_NAME = "Animal";
    private static AnimalManager instance;

    private Map<String, AnimalType> animalTypes = new HashMap<String, AnimalType>();

    private AnimalManager() {
    }

    public static AnimalManager getInstance() {
        if (instance == null) {
            synchronized (AnimalManager.class) {
                if (instance == null) {
                    instance = new AnimalManager();
                    instance.init();
                }
            }
        }
        return instance;
    }

    public Animal createAnimal(String typeName) throws AnimalTypeNotFoundException {
        AnimalType animalType = getAnimalType(typeName);
        Animal result = animalType.createInstance();
        return result;
    }

    private AnimalType getAnimalType(String typeName) throws AnimalTypeNotFoundException {
        Guard.assertArgumentNotNull(typeName, typeName);
        assertIsValidTypeName(typeName);
        return animalTypes.get(typeName);

    }

    public AnimalType addAnimalType(String typeName, int lifeExpectancy) throws AnimalTypeNotFoundException{
        Guard.assertArgumentNotNull(typeName, "typeName");

        return addAnimalType(typeName, null, lifeExpectancy);
    }

    public AnimalType addAnimalType(String typeName, String superTypeName, int lifeExpectancy)
            throws AnimalTypeNotFoundException {
        Guard.assertArgumentNotNull(typeName, "typeName");

        if (animalTypes.containsKey(typeName)) {
            return animalTypes.get(typeName);
        }

        AnimalType superType;
        if (superTypeName != null) {
            assertIsValidTypeName(superTypeName);
            superType = animalTypes.get(superTypeName);
        } else {
            superType = animalTypes.get(ROOT_TYPE_NAME);
        }

        AnimalType subType = new AnimalType(typeName);
        subType.setLifeExpectancy(lifeExpectancy);
        animalTypes.put(typeName, subType);
        superType.addSubType(subType);

        return subType;
    }

    private void assertIsValidTypeName(String typeName) throws AnimalTypeNotFoundException {
        if (!animalTypes.containsKey(typeName)) {
            throw new AnimalTypeNotFoundException(typeName);
        }
    }

    private void init() {
        AnimalType rootType = new AnimalType(ROOT_TYPE_NAME);
        rootType.setLifeExpectancy(20);
        animalTypes.put(ROOT_TYPE_NAME , rootType);
    }
}