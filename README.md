Android Flickr MVP Demo
========

# Welcome to Android Flickr MVP demo prject

This project is demo project uses [Flickr API](https://www.flickr.com/services/api/flickr.photos.search.html)
and applies Android clean architecture descripbed in [Lyubomir Ganev article](http://luboganev.github.io/post/clean-architecture-pt4/)

# Features:

- App supports endless scrolling, automatically requesting and displaying more images when the user scrolls to the bottom of the view.
- App allows to see a history of past searches, by using [SearchRecentSuggestionsProvider] (https://developer.android.com/reference/android/content/SearchRecentSuggestionsProvider.html).
- App correctly handle orientation changes (without requiring android:configChanges in manifest) and work correctly with the “Don’t Keep Activities” developer option enabled.
- The project has working Espresso tests, as well as Unit Tests that run with Robolectric.

It is intended as a sample for my own best practices

### Infomation
- Author：[Oleksii Sosevych](https://github.com/oleksiisosevych)
- Project：[Android-Flickr-MVP-Demo](https://github.com/oleksiisosevych/android-flickr-mvp-demo/)
