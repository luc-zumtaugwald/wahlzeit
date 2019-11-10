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
        return new CityPhoto();
    }

    @Override
    public CityPhoto createPhoto(PhotoId id){
        return new CityPhoto(id);
    }

}