package org.wikipedia.preferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.wikipedia.settings.Prefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Unit tests for Page. */
@RunWith(RobolectricTestRunner.class)
public class ShakeToRelatedTest {

    // Tests Incognito's default false setting
    @Test
    public void testShakeToRelatedDefaultSetting() throws Throwable {
        assertFalse(Prefs.isShakeToRelatedEnabled());
    }

    // Tests Incognito's setter
    @Test
    public void testShakeToRelatedSetFalse() throws Throwable {
        Prefs.setShakeToRelatedEnabled(false);
        assertFalse(Prefs.isShakeToRelatedEnabled());
    }

    // Tests Incognito's setter
    @Test
    public void testShakeToRelatedSetTrue() throws Throwable {
        Prefs.setShakeToRelatedEnabled(true);
        assertTrue(Prefs.isShakeToRelatedEnabled());
    }
}