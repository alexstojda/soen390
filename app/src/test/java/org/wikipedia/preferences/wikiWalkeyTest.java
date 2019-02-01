package org.wikipedia.preferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.wikipedia.settings.Prefs;
import static org.junit.Assert.assertFalse;

/** Unit tests for Page. */
@RunWith(RobolectricTestRunner.class)
public class wikiWalkeyTest {
    @Test
    public void testWikiwalkeyPreferencesDefault() throws Throwable {
        assertFalse(Prefs.isWikiWalkingEnabled());
    }
}
