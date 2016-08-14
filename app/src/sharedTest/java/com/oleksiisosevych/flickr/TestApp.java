package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.data.DataModule;
import com.oleksiisosevych.flickr.di.FlickrApp;
import com.oleksiisosevych.flickr.di.FlickrAppModule;
import com.oleksiisosevych.flickr.view.search.FlickrSearchResultModule;

public class TestApp extends FlickrApp {
    private FlickrAppModule overrideFlickrAppModule;
    private DataModule overrideDataModule;
    private FlickrSearchResultModule overrideFlickrSearchResultModule;

    public void setOverrideFlickrAppModule(FlickrAppModule overrideFlickrAppModule) {
        this.overrideFlickrAppModule = overrideFlickrAppModule;
    }

    public void setOverrideDataModule(DataModule overrideDataModule) {
        this.overrideDataModule = overrideDataModule;
    }

    public void setOverrideFlickrSearchResultModule(FlickrSearchResultModule overrideFlickrSearchResultModule) {
        this.overrideFlickrSearchResultModule = overrideFlickrSearchResultModule;
    }

    @Override
    protected FlickrAppModule getApplicationModule() {
        return overrideFlickrAppModule != null ? overrideFlickrAppModule :
                super.getApplicationModule();
    }

    @Override
    protected DataModule getDataModule() {
        return overrideDataModule != null ? overrideDataModule :
                super.getDataModule();
    }

    @Override
    protected FlickrSearchResultModule getSearchResultModule() {
        return overrideFlickrSearchResultModule != null ? overrideFlickrSearchResultModule :
                super.getSearchResultModule();
    }

}
