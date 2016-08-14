package com.oleksiisosevych.flickr;

import android.content.SearchRecentSuggestionsProvider;


public class FlickrSearchRecentSuggestionsProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "flickr.suggestion.authority";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public FlickrSearchRecentSuggestionsProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }


}
