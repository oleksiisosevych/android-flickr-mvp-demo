package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.data.model.Photo;

import java.util.List;

/**
 * Created by luboganev on 21/04/2015
 */
public interface FlickrSearchPresenterOutput {
    void showProgress();

    void showCurrentProgress(int size, int total);

    void setFlickrImages(List<Photo> images);

    void showWelcomeStatus();

    void showGeneralErrorMsg();
}
