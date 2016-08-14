package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.data.api.FlickrService;
import com.oleksiisosevych.flickr.data.model.PhotoSearchResult;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickrInteractor implements FlickrSearchInteractorInput {
    private FlickrSearchInteractorOutput mPresenter;
    private FlickrService mFlickrService;
    private Call<PhotoSearchResult> mCurrentRequest;

    @Inject
    public FlickrInteractor(FlickrService flickrService) {
        this.mFlickrService = flickrService;
    }

    @Override
    public void setInteractorOutput(FlickrSearchInteractorOutput presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void loadFlickrImages(String query, int page) {
        cancelCurrentRequest();
        mCurrentRequest = mFlickrService.searchPictures(query, page);
        mCurrentRequest.enqueue(new Callback<PhotoSearchResult>() {
            @Override
            public void onResponse(Call<PhotoSearchResult> call,
                                   Response<PhotoSearchResult> response) {
                if (!response.isSuccess()) {
                    mPresenter.onError();
                    return;
                }
                PhotoSearchResult photoSearchResponse = response.body();
                if (!photoSearchResponse.getStat().toLowerCase().equals("ok")) {
                    mPresenter.onError();
                    return;
                }
                mPresenter.onFlickrImagesLoaded(photoSearchResponse);
            }

            @Override
            public void onFailure(Call<PhotoSearchResult> call, Throwable t) {
                if (!t.getMessage().toLowerCase().equals("canceled")) {
                    mPresenter.onError();
                }
            }
        });
    }

    public void cancelCurrentRequest() {
        if (mCurrentRequest != null) {
            mCurrentRequest.cancel();
        }
    }


    @Override
    public void destroy() {
        cancelCurrentRequest();
    }
}
