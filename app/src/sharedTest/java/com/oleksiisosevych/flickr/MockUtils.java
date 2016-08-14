package com.oleksiisosevych.flickr;

import com.oleksiisosevych.flickr.data.model.Photo;
import com.oleksiisosevych.flickr.data.model.PhotoSearchResult;
import com.oleksiisosevych.flickr.data.model.Photos;

import java.util.ArrayList;
import java.util.List;

public final class MockUtils {
    public static final String PHOTO_TITLE_FORMAT = "Test Image : %s";
    public static final int TOTAL_PHOTOS = 333;

    private MockUtils() {
    }

    public static List<Photo> createStubPhotoList() {
        List<Photo> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            result.add(new Photo.Builder().title(String.format(PHOTO_TITLE_FORMAT, i)).build());
        }
        return result;
    }

    public static PhotoSearchResult createStubPhotoSearchResult() {
        List<Photo> stubPhotoList = createStubPhotoList();
        Photos photos = new Photos.Builder()
                .page(1)
                .photo(stubPhotoList)
                .pages((int) Math.ceil(TOTAL_PHOTOS / stubPhotoList.size()))
                .perpage(stubPhotoList.size())
                .total(Integer.toString(TOTAL_PHOTOS))
                .build();
        return new PhotoSearchResult.Builder()
                .photos(photos)
                .build();
    }
}
