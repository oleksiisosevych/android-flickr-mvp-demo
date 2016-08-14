package com.oleksiisosevych.flickr;

import android.support.annotation.NonNull;

/**
 * Created by luboganev on 21/04/2015
 */
public interface FlickrSearchPresenterInput {
    void setView(@NonNull FlickrSearchPresenterOutput view);

    void onViewShow();

    void searchQueryChanged(String query);

    void destroy();

    void onEndOfListReached();
}
