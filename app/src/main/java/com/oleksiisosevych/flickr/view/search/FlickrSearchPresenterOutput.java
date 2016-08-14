package com.oleksiisosevych.flickr.view.search;

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

    void showLogo();

    void hideLogo();

    void showGeneralErrorMsg();
}
