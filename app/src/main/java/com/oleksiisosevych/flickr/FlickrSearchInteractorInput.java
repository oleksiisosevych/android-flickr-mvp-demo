package com.oleksiisosevych.flickr;

public interface FlickrSearchInteractorInput {
    void setInteractorOutput(FlickrSearchInteractorOutput presenter);

    void loadFlickrImages(String query, int page);

    void destroy();
}
