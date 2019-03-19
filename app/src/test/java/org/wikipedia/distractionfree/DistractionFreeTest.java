package org.wikipedia.distractionfree;

import org.junit.Test;
import org.wikipedia.settings.Prefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DistractionFreeTest {

    @Test
    public void distractionFreeDefault() {
        assertFalse(Prefs.isDistractionFreeModeEnabled());
    }

    @Test
    public void testIncognitoSetFalse() {
        Prefs.disableDistractionFreeMode();
        assertFalse(Prefs.isWikiWalkingEnabled());
    }

    @Test
    public void testIncognitoSetTrue()  {
        Prefs.enableDistractionFreeMode();
        assertTrue(Prefs.isDistractionFreeModeEnabled());
    }
}
