package com.oleksiisosevych.flickr.di;

import android.app.Application;
import android.content.Context;
//import android.support.multidex.MultiDex;


public class FlickrApp extends Application {

    private AppComponent component;

    public static FlickrApp get(Context context) {
        return (FlickrApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        component = DaggerAppComponent.builder()
                .flickrAppModule(new FlickrAppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return component;
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
}
