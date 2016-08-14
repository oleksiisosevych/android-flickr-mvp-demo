package com.oleksiisosevych.flickr;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;
import android.widget.EditText;

import com.oleksiisosevych.flickr.data.model.Photo;
import com.oleksiisosevych.flickr.view.search.FlickrSearchActivity;
import com.oleksiisosevych.flickr.view.search.FlickrSearchFragment;
import com.oleksiisosevych.flickr.view.search.FlickrSearchPresenterInput;
import com.oleksiisosevych.flickr.view.search.FlickrSearchPresenterOutput;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.oleksiisosevych.flickr.utils.TestUtils.withRecyclerView;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FlickrSearchFragmentTests {
    @Rule public final ActivityTestRule<FlickrSearchActivity> main =
            new ActivityTestRule<>(FlickrSearchActivity.class, false, false);

    @Rule public final TestAppComponentRule componentRule = new TestAppComponentRule();

    private FlickrSearchPresenterInput mMockPresenter;
    private FlickrSearchFragment mFragment;



    @Before
    public void setup() throws Throwable {
        // Set up application module
        mMockPresenter = mock(FlickrSearchPresenterInput.class);
        MockFickrSearchResultModule mockFickrSearchResultModule = componentRule.getMockFickrSearchResultModule();
        mockFickrSearchResultModule.setOverrideFlickrSearchPresenterInput(mMockPresenter);

        // Launch main activity
        main.launchActivity(FlickrSearchActivity.getStartIntent(getTargetContext()));
    }

    @Test
    public void testInitialAppearance() throws Throwable {
        // Check greetings msg is displayed
        onView(withId(R.id.status_msg)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).check(matches(isDisplayed()));

        verify(mMockPresenter, times(1)).setView(any(FlickrSearchPresenterOutput.class));
        verify(mMockPresenter, times(1)).onViewShow();
    }

    @Test
    public void testSearchQuerySubmission() throws Throwable {
        String stringToBeTyped = "Test Search";
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText(stringToBeTyped), pressKey(KeyEvent.KEYCODE_ENTER));

        // Issue with emulator keyboard, fire two events on "return" tap therefore usint atLeastOnce()
        // check instead times(1)- >
        // https://archive.appcelerator.com/question/127166/android-search-keyboardtype-fires-return-event-twice
        verify(mMockPresenter, atLeastOnce()).searchQueryChanged(stringToBeTyped);
    }

    @Test
    public void testSearchResultDisplay() throws Throwable {
        setupFragment();
        final List<Photo> stubPhotoList = MockUtils.createStubPhotoList();
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFragment.setFlickrImages(stubPhotoList);
            }
        });

        // Check item at position 0 has expected title
        onView(withRecyclerView(R.id.recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText(String.format(MockUtils.PHOTO_TITLE_FORMAT, 0)))));

        // Scroll to bottom and check if Presenter was notified about this
        int lastPosition = stubPhotoList.size() - 1;
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(lastPosition, click()));

        verify(mMockPresenter, times(1)).onEndOfListReached();
    }

    private void setupFragment() {
        mFragment = FlickrSearchFragment.newInstance();
        FragmentManager fragmentManager = main.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, mFragment)
                .commit();

        // Wait for the fragment to be committed
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
    }
}
