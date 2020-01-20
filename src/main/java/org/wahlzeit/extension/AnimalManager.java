package org.wahlzeit.extension;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.services.ObjectManager;

/**
 * AnimalManager
 */
public class AnimalManager {

    private static AnimalManager instance;

    private Map<String, AnimalType> animalTypes = new HashMap<String, AnimalType>();

    private AnimalManager(){}

    public static AnimalManager getInstance(){
        if(instance == null){
            synchronized(AnimalManager.class){
                if(instance == null){
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

    public AnimalType addAnimalType(String typeName){
        Guard.assertArgumentNotNull(typeName, "typeName");
        
        if(animalTypes.containsKey(typeName)){
            return animalTypes.get(typeName);
        }

        AnimalType type = new AnimalType(typeName);
        animalTypes.put(typeName, type);
        return type;
    }

    public AnimalType addAnimalType(String typeName, String superTypeName) throws AnimalTypeNotFoundException {
        Guard.assertArgumentNotNull(typeName, "typeName");
        Guard.assertArgumentNotNull(superTypeName, "superTypeName");
        assertIsValidTypeName(superTypeName);

        AnimalType superType = animalTypes.get(superTypeName);
        AnimalType subType = addAnimalType(typeName);
        superType.addSubType(subType);
        
        return subType;
    }

    private void assertIsValidTypeName(String typeName) throws AnimalTypeNotFoundException{
        if(!animalTypes.containsKey(typeName)){
            throw new AnimalTypeNotFoundException(typeName);
        }
    }
    private void init(){
        addAnimalType("Animal");
        try{
            addAnimalType("Unknown", "Animal");
        } catch(AnimalTypeNotFoundException ex){

        }
        
    }
}