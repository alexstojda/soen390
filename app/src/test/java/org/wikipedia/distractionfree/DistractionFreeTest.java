package org.wikipedia.distractionfree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.wikipedia.settings.Prefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class DistractionFreeTest {

    @Test
    public void distractionFreeDefault() {
        assertFalse(Prefs.isDistractionFreeModeEnabled());
    }

    @Test
    public void testDFSetFalse() {
        Prefs.disableDistractionFreeMode();
        assertFalse(Prefs.isWikiWalkingEnabled());
    }

    @Test
    public void testDFSetTrue() {
        Prefs.enableDistractionFreeMode();
        assertTrue(Prefs.isDistractionFreeModeEnabled());
    }
}
