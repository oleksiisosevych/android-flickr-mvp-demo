package com.oleksiisosevych.flickr.view.search;

import android.support.annotation.NonNull;

import com.oleksiisosevych.flickr.data.model.Photo;
import com.oleksiisosevych.flickr.data.model.PhotoSearchResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FlickrSearchPresenter implements FlickrSearchPresenterInput, FlickrSearchInteractorOutput {
    private FlickrSearchPresenterOutput mView;
    private List<Photo> mLoadedData;
    private FlickrSearchInteractorInput mInteractor;
    private String mSearchQuery;
    private int mCurrentPage = 1;
    private boolean mLoading;

    private int mTotalImagesForCurrentQuery;
    private int mPagesTotalForCurrentQuery;

    @Inject
    public FlickrSearchPresenter(FlickrSearchInteractorInput interactor) {
        this.mInteractor = interactor;
    }

    @Override
    public void setView(@NonNull FlickrSearchPresenterOutput view) {
        this.mView = view;
    }

    @Override
    public void onViewShow() {
        if (mLoadedData != null) {
            mView.setFlickrImages(mLoadedData);
            if (mLoading) {
                mView.showProgress();
            } else {
                mView.showCurrentProgress(mLoadedData.size(), mTotalImagesForCurrentQuery);
            }
            return;
        }

        if (mSearchQuery != null && !mSearchQuery.isEmpty()) {
            reload();
        } else {
            mView.showWelcomeStatus();
        }

    }

    private void reload() {
        mView.showProgress();
        if (mLoadedData == null) {
            mLoadedData = new ArrayList<>();
        } else {
            mLoadedData.clear();
        }
        mLoading = true;
        mInteractor.loadFlickrImages(mSearchQuery, 1);
    }

    @Override
    public void searchQueryChanged(String query) {
        mSearchQuery = query;
        reload();
    }

    @Override
    public void destroy() {
        mInteractor.destroy();
    }

    @Override
    public void onEndOfListReached() {
        if (mPagesTotalForCurrentQuery > mCurrentPage) {
            mLoading = true;
            mInteractor.loadFlickrImages(mSearchQuery, mCurrentPage + 1);
            mView.showProgress();
        }
    }

    @Override
    public void onFlickrImagesLoaded(PhotoSearchResult result) {
        mLoading = false;
        updateSearchResultInfo(result);
        mLoadedData.addAll(result.getPhotos().getPhoto());
        mView.showCurrentProgress(mLoadedData.size(), mTotalImagesForCurrentQuery);
        mView.setFlickrImages(mLoadedData);
    }

    @Override
    public void onError() {
        mView.showGeneralErrorMsg();
    }

    private void updateSearchResultInfo(PhotoSearchResult result) {
        mTotalImagesForCurrentQuery = Integer.parseInt(result.getPhotos().getTotal());
        mPagesTotalForCurrentQuery = result.getPhotos().getPages();
        mCurrentPage = result.getPhotos().getPage();
    }
}
