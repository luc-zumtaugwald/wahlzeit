package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

/**
 * CityPhotoFactory
 */
public class CityPhotoFactory extends PhotoFactory {

    
    protected CityPhotoFactory(){
        super();
    }
    
    @Override
    public CityPhoto createPhoto() {
        // TODO Auto-generated method stub
        CityPhoto photo = new CityPhoto();
        photo.setCityName("unknown");
        photo.setInhabitantCount(0);
        return photo;
    }
    
    @Override
    public CityPhoto createPhoto(PhotoId id){
        CityPhoto photo = new CityPhoto(id);
        photo.setCityName("unknown");
        photo.setInhabitantCount(0);
        return photo;
    }

}