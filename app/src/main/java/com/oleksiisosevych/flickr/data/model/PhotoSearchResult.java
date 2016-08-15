
package com.oleksiisosevych.flickr.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoSearchResult {

    @SerializedName("photos") @Expose private Photos photos;
    @SerializedName("stat") @Expose private String stat;

    private PhotoSearchResult(Builder builder) {
        setPhotos(builder.photos);
        setStat(builder.stat);
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public static final class Builder {
        private Photos photos;
        private String stat;

        public Builder() {
        }

        public Builder photos(Photos val) {
            photos = val;
            return this;
        }

        public Builder stat(String val) {
            stat = val;
            return this;
        }

        public PhotoSearchResult build() {
            return new PhotoSearchResult(this);
        }
    }
}
