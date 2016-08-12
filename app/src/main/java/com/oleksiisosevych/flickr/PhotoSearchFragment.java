package com.oleksiisosevych.flickr;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oleksiisosevych.flickr.data.api.FlickrService;
import com.oleksiisosevych.flickr.data.model.Photo;
import com.oleksiisosevych.flickr.data.model.PhotosSearchResponse;
import com.oleksiisosevych.flickr.di.FlickrApp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotoSearchFragment extends Fragment {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.status_msg) TextView statusMsg;

    @Inject FlickrService flickrService;

    private List<Photo> photoList = new ArrayList<>();
    private PhotoSearchAdapter adapter;
    private String currentStatusMsg;

    public PhotoSearchFragment() {
        // Required empty public constructor
    }

    public static PhotoSearchFragment newInstance() {
        return new PhotoSearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        adapter = new PhotoSearchAdapter(getActivity(), photoList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(PhotoSearchFragment.class.getSimpleName(), "onCreateView");
        View view = inflater.inflate(R.layout.fragment_pictures, container, false);
        ButterKnife.bind(this, view);
        FlickrApp.get(getActivity()).getAppComponent().inject(this);
        int columnsNumber = getResources().getInteger(R.integer.columns_number);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columnsNumber));
        recyclerView.setAdapter(adapter);
        updateCurrentStatusMsg();
        return view;
    }

    private void updateCurrentStatusMsg() {
        statusMsg.setText(currentStatusMsg);
    }

    private void searchPictures(String searchText) {
        currentStatusMsg = getString(R.string.status_loading);
        updateCurrentStatusMsg();
        photoList.clear();
        adapter.notifyDataSetChanged();
        Call<PhotosSearchResponse> searchPhotoRequest = flickrService.searchPictures(searchText);
        searchPhotoRequest.enqueue(new Callback<PhotosSearchResponse>() {
            @Override
            public void onResponse(Call<PhotosSearchResponse> call,
                                   Response<PhotosSearchResponse> response) {
                if (response.isSuccess()) {
                    PhotosSearchResponse photoSearchResponse = response.body();
                    if (photoSearchResponse.getStat().toLowerCase().equals("ok")) {
                        photoList.addAll(photoSearchResponse.getPhotos().getPhoto());
                        adapter.notifyDataSetChanged();
                        currentStatusMsg = getString(R.string.status_showing_pictures_format,
                                photoList.size(), photoSearchResponse.getPhotos().getTotal());
                        updateCurrentStatusMsg();
                    }
                }
            }

            @Override
            public void onFailure(Call<PhotosSearchResponse> call, Throwable t) {
                currentStatusMsg = getString(R.string.api_problems);
                updateCurrentStatusMsg();
            }
        });
    }

    public void requestSearch(String query) {
        searchPictures(query);
    }
}
