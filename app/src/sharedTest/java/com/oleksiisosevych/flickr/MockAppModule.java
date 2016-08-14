package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.di.FlickrApp;
import com.oleksiisosevych.flickr.di.FlickrAppModule;

public class MockAppModule extends FlickrAppModule {

    public MockAppModule(FlickrApp app) {
        super(app);
    }
}
