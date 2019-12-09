package org.wahlzeit.extension;

import org.wahlzeit.model.PhotoId;

/**
 * PhotoCreationFailedException
 */
public class PhotoCreationFailedException extends Exception {
    
    private PhotoId photoId;

    public PhotoCreationFailedException(PhotoId id, Throwable cause){
        super(getMessage(id, cause), cause);
        this.photoId = id;     
    }

    /**
     * @methodType get
     * @return PhotoId
     */
    public PhotoId getPhotoId(){
        return photoId;
    }

    private static String getMessage(PhotoId id, Throwable cause){
        String idString = (id == null) ? "" : ("with ID "+id.toString()+" ");
        return "Photo "+idString+"could not be created. (Cause: "+cause.getMessage()+")";
    }
}