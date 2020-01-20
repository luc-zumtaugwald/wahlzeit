package org.wahlzeit.extension;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import com.googlecode.objectify.annotation.Subclass;

/**
 * AnimalPhoto
 */

@Subclass
@PatternInstance(
	patternName = "Abstract Factory",
	participants = {
		"ConcreteProduct"
	}
)
public class AnimalPhoto extends Photo {
    
    protected Animal animal;

    public AnimalPhoto(){
        super();
    }
    public AnimalPhoto(PhotoId id){
        super(id);
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    

    
    
}