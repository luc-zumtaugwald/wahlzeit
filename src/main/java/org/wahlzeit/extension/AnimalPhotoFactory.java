package org.wahlzeit.extension;

import java.util.logging.Logger;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;

/**
 * CityPhotoFactory
 */
@PatternInstance(
	patternName = "Abstract Factory",
	participants = {
		"ConcreteFactory"
	}
)
public class AnimalPhotoFactory extends PhotoFactory {

    
    public AnimalPhotoFactory(){
        super();
    }
    
    @Override
    public AnimalPhoto createPhoto() throws PhotoCreationFailedException {
        AnimalPhoto photo;
        try{
            photo = doCreatePhoto();
        } catch (IllegalArgumentException ex){
            throw new PhotoCreationFailedException(null, ex);
        } catch (AnimalTypeNotFoundException ex){
            throw new PhotoCreationFailedException(null, ex);
        }
        
        return photo;
    }
    
    @Override
    public AnimalPhoto createPhoto(PhotoId id) throws PhotoCreationFailedException {
        AnimalPhoto photo;
        try{
            photo = doCreatePhoto(id);
        } catch (IllegalArgumentException ex){
            throw new PhotoCreationFailedException(id, ex);
        } catch (AnimalTypeNotFoundException ex){
            throw new PhotoCreationFailedException(id, ex);
        }
        return photo;
    }

    private AnimalPhoto doCreatePhoto() throws AnimalTypeNotFoundException {
        return doCreatePhoto(null);
    }

    private AnimalPhoto doCreatePhoto(PhotoId id) throws AnimalTypeNotFoundException {
        AnimalManager manager = AnimalManager.getInstance();
        Animal animal = manager.createAnimal("Unknown");
        animal.setLocation(new Location(CartesianCoordinate.getCartesianCoordinate()));
        AnimalPhoto photo;
        if(id == null){
            photo = new AnimalPhoto();
        } else {
            photo = new AnimalPhoto(id);
        }
        photo.setAnimal(animal);
        return photo;
    }
}