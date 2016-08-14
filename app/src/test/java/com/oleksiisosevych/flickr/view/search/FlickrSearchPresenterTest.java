package com.oleksiisosevych.flickr.view.search;

import com.oleksiisosevych.flickr.BuildConfig;
import com.oleksiisosevych.flickr.MockUtils;
import com.oleksiisosevych.flickr.TestApp;
import com.oleksiisosevych.flickr.data.model.Photo;
import com.oleksiisosevych.flickr.data.model.PhotoSearchResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        application = TestApp.class,
        sdk = 21)
public class FlickrSearchPresenterTest {
    public static final String TEST_QUERY = "Test Query";
    private FlickrSearchInteractorInput mockInteractor;
    private FlickrSearchPresenterOutput mockView;

    @Before
    public void setup() {
        mockInteractor = mock(FlickrInteractor.class);
        mockView = mock(FlickrSearchPresenterOutput.class);
    }

    @Test
    public void testInitialLoadingIsCalledCorrectly() {
        FlickrSearchPresenter presenter = new FlickrSearchPresenter(mockInteractor);
        presenter.setView(mockView);
        presenter.onViewShow();

        verify(mockView, times(1)).showWelcomeStatus();
        verify(mockView, never()).showProgress();
        verify(mockView, never()).showGeneralErrorMsg();
    }

    @Test
    public void testSearchRequestReceived() {
        final FlickrSearchPresenter presenter = new FlickrSearchPresenter(mockInteractor);
        presenter.setView(mockView);

        final PhotoSearchResult mockServerAnswer = MockUtils.createStubPhotoSearchResult();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onFlickrImagesLoaded(mockServerAnswer);
                return null;
            }
        }).when(mockInteractor).loadFlickrImages(anyString(), anyInt());

        presenter.searchQueryChanged(TEST_QUERY);

        verify(mockInteractor, times(1)).loadFlickrImages(TEST_QUERY, 1);
        verify(mockView, times(1)).showProgress();
        verify(mockView, times(1)).setFlickrImages(mockServerAnswer.getPhotos().getPhoto());
        verify(mockView, times(1)).showCurrentProgress(mockServerAnswer.getPhotos().getPhoto().size(),
                Integer.parseInt(mockServerAnswer.getPhotos().getTotal()));
    }

    @Test
    public void testServerError() {
        final FlickrSearchPresenter presenter = new FlickrSearchPresenter(mockInteractor);
        presenter.setView(mockView);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onError();
                return null;
            }
        }).when(mockInteractor).loadFlickrImages(anyString(), anyInt());

        presenter.searchQueryChanged(TEST_QUERY);

        verify(mockInteractor, times(1)).loadFlickrImages(TEST_QUERY, 1);
        verify(mockView, times(1)).showProgress();
        verify(mockView, times(1)).showGeneralErrorMsg();
        verify(mockView, never()).setFlickrImages(anyListOf(Photo.class));
        verify(mockView, never()).showCurrentProgress(anyInt(), anyInt());
    }
}