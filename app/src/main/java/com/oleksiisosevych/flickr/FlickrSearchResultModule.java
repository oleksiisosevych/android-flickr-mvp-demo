package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.data.api.FlickrService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class FlickrSearchResultModule {

    @Provides
    @Singleton
    FlickrSearchPresenterInput provideFlickrPresenter(FlickrSearchInteractorInput interactor) {
        FlickrSearchPresenter presenter = new FlickrSearchPresenter(interactor);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }

    @Provides
    @Singleton
    FlickrSearchInteractorInput provideFlickrInteractor(FlickrService service) {
        return new FlickrInteractor(service);
    }
}
