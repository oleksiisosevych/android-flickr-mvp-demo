package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.data.api.FlickrService;
import com.oleksiisosevych.flickr.view.search.FlickrSearchInteractorInput;
import com.oleksiisosevych.flickr.view.search.FlickrSearchPresenterInput;
import com.oleksiisosevych.flickr.view.search.FlickrSearchResultModule;

public class MockFickrSearchResultModule extends FlickrSearchResultModule {
    private FlickrSearchPresenterInput overrideFlickrSearchPresenterInput;
    private FlickrSearchInteractorInput overrideFlickrSearchInteractorInput;

    public void setOverrideFlickrSearchPresenterInput(FlickrSearchPresenterInput overrideFlickrSearchPresenterInput) {
        this.overrideFlickrSearchPresenterInput = overrideFlickrSearchPresenterInput;
    }

    public void setOverrideFlickrSearchInteractorInput(FlickrSearchInteractorInput overrideFlickrSearchInteractorInput) {
        this.overrideFlickrSearchInteractorInput = overrideFlickrSearchInteractorInput;
    }

    @Override
    public FlickrSearchPresenterInput provideFlickrPresenter(FlickrSearchInteractorInput interactor) {
        return overrideFlickrSearchPresenterInput != null ? overrideFlickrSearchPresenterInput :
                super.provideFlickrPresenter(interactor);
    }

    @Override
    public FlickrSearchInteractorInput provideFlickrInteractor(FlickrService service) {
        return overrideFlickrSearchInteractorInput != null ? overrideFlickrSearchInteractorInput :
                super.provideFlickrInteractor(service);
    }
}
