package org.wahlzeit.model;

/**
 * CityPhoto
 */
public class CityPhoto extends Photo {
    
    protected String cityName;
    
    public CityPhoto(){
        super();
    }
    public CityPhoto(PhotoId id){
        super(id);
    }
    public String getCityName(){
        return cityName;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    
}