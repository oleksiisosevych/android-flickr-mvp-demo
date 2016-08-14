
package com.oleksiisosevych.flickr.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PhotoSearchResult {

    @SerializedName("photos") @Expose private Photos photos;
    @SerializedName("stat") @Expose private String stat;

    /**
     * @return The photos
     */
    public Photos getPhotos() {
        return photos;
    }

    /**
     * @param photos The photos
     */
    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    /**
     * @return The stat
     */
    public String getStat() {
        return stat;
    }

    /**
     * @param stat The stat
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

}
