package com.oleksiisosevych.flickr.di;

import android.app.Application;
import android.content.Context;

import com.oleksiisosevych.flickr.data.DataModule;
import com.oleksiisosevych.flickr.view.search.FlickrSearchResultModule;
//import android.support.multidex.MultiDex;


public class FlickrApp extends Application {

    private AppComponent component;

    public static AppComponent getAppComponent(Context context) {
        FlickrApp app = (FlickrApp) context.getApplicationContext();
        if (app.component == null) {
            app.component = DaggerAppComponent.builder()
                    .flickrAppModule(app.getApplicationModule())
                    .dataModule(app.getDataModule())
                    .flickrSearchResultModule(app.getSearchResultModule())
                    .build();
        }
        return app.component;
    }

    public static void clearAppComponent(Context context) {
        FlickrApp app = (FlickrApp) context.getApplicationContext();
        app.component = null;
    }

    protected FlickrAppModule getApplicationModule() {
        return new FlickrAppModule(this);
    }

    protected DataModule getDataModule() {
        return new DataModule();
    }

    protected FlickrSearchResultModule getSearchResultModule() {
        return new FlickrSearchResultModule();
    }
}
