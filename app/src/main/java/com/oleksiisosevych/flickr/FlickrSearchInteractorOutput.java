package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.data.model.PhotoSearchResult;

public interface FlickrSearchInteractorOutput {
    void onFlickrImagesLoaded(PhotoSearchResult result);

    void onError();
}
