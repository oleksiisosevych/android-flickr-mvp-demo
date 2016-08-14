package com.oleksiisosevych.flickr.view.search;

import com.oleksiisosevych.flickr.data.api.FlickrService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FlickrSearchResultModule {

    @Provides
    @Singleton
    public FlickrSearchPresenterInput provideFlickrPresenter(FlickrSearchInteractorInput interactor) {
        FlickrSearchPresenter presenter = new FlickrSearchPresenter(interactor);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }

    @Provides
    @Singleton
    public FlickrSearchInteractorInput provideFlickrInteractor(FlickrService service) {
        return new FlickrInteractor(service);
    }
}
