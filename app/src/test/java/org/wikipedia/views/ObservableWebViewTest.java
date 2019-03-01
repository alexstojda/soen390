package org.wikipedia.views;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class ObservableWebViewTest {

    @Test
    public void testOnScrollChangedIsNullByDefault() {

        ObservableWebView owv = new ObservableWebView(RuntimeEnvironment.application);
        ObservableWebView.OnScrollChangedCallback test = owv.getOnScrollChangedCallback();
        assertNull(test);
    }

    @Test
    public void testOnScrollSetAndGet() {

        ObservableWebView owv = new ObservableWebView(RuntimeEnvironment.application);
        ObservableWebView.OnScrollChangedCallback mockScroll= mock(ObservableWebView.OnScrollChangedCallback.class);

        owv.setOnScrollChangedCallback(mockScroll);
        ObservableWebView.OnScrollChangedCallback test = owv.getOnScrollChangedCallback();

        assertNotNull(test);
    }
}