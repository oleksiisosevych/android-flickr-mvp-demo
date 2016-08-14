package com.oleksiisosevych.flickr.view.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.oleksiisosevych.flickr.R;

public class FlickrSearchActivity extends AppCompatActivity {

    private FlickrSearchFragment picturesFragment;
    private SearchRecentSuggestions suggestions =
            new SearchRecentSuggestions(this,
                    FlickrSearchRecentSuggestionsProvider.AUTHORITY,
                    FlickrSearchRecentSuggestionsProvider.MODE);

    public static Intent getStartIntent(Context context) {
        return new Intent(context, FlickrSearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the retained fragment on activity restarts
        String tag = FlickrSearchFragment.class.getSimpleName();
        FragmentManager fm = getSupportFragmentManager();
        picturesFragment = (FlickrSearchFragment) fm.findFragmentByTag(tag);

        // create the fragment for the first time
        if (picturesFragment == null) {
            picturesFragment = FlickrSearchFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.content, picturesFragment, tag)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            suggestions.saveRecentQuery(query, null);
            picturesFragment.requestSearch(query);
        }
    }
}
