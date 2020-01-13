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
public class CityPhotoFactory extends PhotoFactory {

    
    public CityPhotoFactory(){
        super();
    }
    
    @Override
    public CityPhoto createPhoto() throws PhotoCreationFailedException {
        CityPhoto photo;
        try{
            photo = doCreatePhoto();
        } catch (IllegalArgumentException ex){
            throw new PhotoCreationFailedException(null, ex);
        }
        
        return photo;
    }
    
    @Override
    public CityPhoto createPhoto(PhotoId id) throws PhotoCreationFailedException {
        CityPhoto photo;
        try{
            photo = doCreatePhoto(id);
        } catch (IllegalArgumentException ex){
            throw new PhotoCreationFailedException(id, ex);
        }
        return photo;
    }

    private CityPhoto doCreatePhoto(){
        CityPhoto photo = new CityPhoto();
        photo.setCityName("unknown");
        photo.setInhabitantCount(0);
        return photo;
    }

    private CityPhoto doCreatePhoto(PhotoId id){
        CityPhoto photo = new CityPhoto(id);
        photo.setCityName("unknown");
        photo.setInhabitantCount(0);
        return photo;
    }
}