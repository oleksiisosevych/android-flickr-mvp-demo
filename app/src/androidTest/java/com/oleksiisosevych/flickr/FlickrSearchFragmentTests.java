package com.oleksiisosevych.flickr;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;
import android.test.suitebuilder.annotation.LargeTest;

import com.oleksiisosevych.flickr.view.search.FlickrSearchActivity;
import com.oleksiisosevych.flickr.view.search.FlickrSearchFragment;
import com.oleksiisosevych.flickr.view.search.FlickrSearchPresenterInput;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FlickrSearchFragmentTests {
    @Rule public final ActivityTestRule<FlickrSearchActivity> main =
            new ActivityTestRule<>(FlickrSearchActivity.class, false, false);

    @Rule public final TestAppComponentRule componentRule = new TestAppComponentRule();

    private FlickrSearchPresenterInput mMockPresenter;

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
    public void testLoadingDisplaysCorrectly() throws Throwable {
        // Check greetings msg is displayed
        onView(withId(R.id.status_msg)).check(matches(isDisplayed()));
    }

    private void setupFragment() {
        FragmentManager fragmentManager = main.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, FlickrSearchFragment.newInstance())
                .commit();

        // Wait for the fragment to be committed
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
    }
}
