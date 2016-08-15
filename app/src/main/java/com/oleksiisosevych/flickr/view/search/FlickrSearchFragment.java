package com.oleksiisosevych.flickr.view.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oleksiisosevych.flickr.R;
import com.oleksiisosevych.flickr.data.api.FlickrService;
import com.oleksiisosevych.flickr.data.model.Photo;
import com.oleksiisosevych.flickr.di.FlickrApp;
import com.oleksiisosevych.flickr.view.common.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FlickrSearchFragment extends Fragment implements FlickrSearchPresenterOutput {
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.status_msg) TextView mStatusMsg;
    @BindView(R.id.logo) View mLogo;


    @Inject FlickrSearchPresenterInput mPresenter;

    @Inject FlickrService flickrService;

    private List<Photo> photoList = new ArrayList<>();
    private FlickrImagesAdapter adapter;

    public FlickrSearchFragment() {
        // Required empty public constructor
    }

    public static FlickrSearchFragment newInstance() {
        return new FlickrSearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        adapter = new FlickrImagesAdapter(getActivity(), photoList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pictures, container, false);

        ButterKnife.bind(this, view);
        FlickrApp.getAppComponent(getActivity()).inject(this);

        mPresenter.setView(this);

        int columnsNumber = getResources().getInteger(R.integer.columns_number);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columnsNumber);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                mPresenter.onEndOfListReached();
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onViewShow();
    }

    public void requestSearch(String query) {
        mPresenter.searchQueryChanged(query);
    }

    @Override
    public void showProgress() {
        mStatusMsg.setText(getString(R.string.status_loading));
    }

    @Override
    public void showCurrentProgress(int size, int total) {
        mStatusMsg.setText(getString(R.string.status_showing_pictures_format, size, total));
    }

    @Override
    public void setFlickrImages(List<Photo> images) {
        photoList.clear();
        photoList.addAll(images);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showWelcomeStatus() {
        mStatusMsg.setText(getString(R.string.welcome_msg));
    }

    @Override
    public void showLogo() {
        mLogo.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLogo() {
        mLogo.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGeneralErrorMsg() {
        mStatusMsg.setText(getString(R.string.general_error_msg));

    }

}
