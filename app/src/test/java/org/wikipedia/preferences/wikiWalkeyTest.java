package org.wikipedia.preferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.wikipedia.dataclient.WikiSite;
import org.wikipedia.settings.Prefs;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/** Unit tests for Page. */
@RunWith(RobolectricTestRunner.class)
public class wikiWalkeyTest {
    @Test
    public void testWikiwalkeyPreferencesDefault() throws Throwable {
        assertThat(Prefs.isWikiWalkingEnabled(),is(false));
    }
}
