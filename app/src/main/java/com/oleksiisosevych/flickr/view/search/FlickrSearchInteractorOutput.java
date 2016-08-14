package com.oleksiisosevych.flickr.view.search;

import com.oleksiisosevych.flickr.data.model.PhotoSearchResult;

public interface FlickrSearchInteractorOutput {
    void onFlickrImagesLoaded(PhotoSearchResult result);

    void onError();
}
