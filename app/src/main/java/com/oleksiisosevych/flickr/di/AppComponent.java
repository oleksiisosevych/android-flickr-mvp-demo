package com.oleksiisosevych.flickr.di;

import com.oleksiisosevych.flickr.FlickrSearchResultModule;
import com.oleksiisosevych.flickr.PhotoSearchFragment;
import com.oleksiisosevych.flickr.data.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * The core component for Ayuda applications
 */
@Singleton
@Component(modules = {FlickrAppModule.class, DataModule.class, FlickrSearchResultModule.class})
public interface AppComponent {

    void inject(PhotoSearchFragment fragment);

}