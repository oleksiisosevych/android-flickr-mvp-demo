package com.oleksiisosevych.flickr.data.api;

import com.oleksiisosevych.flickr.data.model.PhotosSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {
    @GET("/services/rest/?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1")
    Call<PhotosSearchResponse> searchPictures(@Query("text") String searchText,
                                              @Query("page") int page);
}
