package org.wikipedia.preferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.wikipedia.settings.Prefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Unit tests for Page. */
@RunWith(RobolectricTestRunner.class)
public class WikiWalkeyTest {
    @Test
    public void testWikiWalkeyPreferencesDefault() throws Throwable {
        assertFalse(Prefs.isWikiWalkingEnabled());
    }

    // Tests WikiWalkey's setter
    @Test
    public void testIncognitoSetFalse() throws Throwable {
        Prefs.setWikiWalkeyEnabled(false);
        assertFalse(Prefs.isIncognitoEnabled());
    }

    // Tests WikiWalkey's setter
    @Test
    public void testIncognitoSetTrue() throws Throwable {
        Prefs.setWikiWalkeyEnabled(true);
        assertTrue(Prefs.isIncognitoEnabled());
    }
}
