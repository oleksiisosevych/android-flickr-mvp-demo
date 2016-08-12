package com.oleksiisosevych.flickr.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class FlickrAppModule {
    private final FlickrApp app;

    public FlickrAppModule(FlickrApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}
