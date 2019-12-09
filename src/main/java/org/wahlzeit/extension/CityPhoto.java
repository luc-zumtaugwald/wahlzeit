package org.wahlzeit.extension;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import com.googlecode.objectify.annotation.Subclass;

/**
 * CityPhoto
 */

@Subclass
public class CityPhoto extends Photo {
    
    protected String cityName;
    protected int inhabitantCount;

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

    public int getInhabitantCount() {
        return inhabitantCount;
    }

    public void setInhabitantCount(int inhabitantCount) {
        Guard.assertArgumentGreaterOrEqualThan(inhabitantCount, 0, false, "inhabitantCount");
        this.inhabitantCount = inhabitantCount;
    }

    
    
}