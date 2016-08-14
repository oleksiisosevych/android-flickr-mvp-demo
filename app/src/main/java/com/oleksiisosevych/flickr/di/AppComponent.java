package com.oleksiisosevych.flickr.di;


import com.oleksiisosevych.flickr.data.DataModule;
import com.oleksiisosevych.flickr.view.search.FlickrSearchFragment;
import com.oleksiisosevych.flickr.view.search.FlickrSearchResultModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * The core component for Flick application
 */
@Singleton
@Component(modules = {FlickrAppModule.class, DataModule.class, FlickrSearchResultModule.class})
public interface AppComponent {

    void inject(FlickrSearchFragment fragment);

}