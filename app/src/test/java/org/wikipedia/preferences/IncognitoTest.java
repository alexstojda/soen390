package org.wikipedia.preferences;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.robolectric.RobolectricTestRunner;
        import org.wikipedia.settings.Prefs;

        import static org.junit.Assert.assertTrue;
        import static org.junit.Assert.assertFalse;

/** Unit tests for Page. */
@RunWith(RobolectricTestRunner.class)
public class IncognitoTest {

    // Tests Incognito's default false setting
    @Test
    public void testIncognitoDefaultSetting() throws Throwable {
        assertFalse(Prefs.isIncognitoEnabled());
    }

    // Tests Incognito's setter
    @Test
    public void testIncognitoSetFalse() throws Throwable {
        Prefs.setIncognitoEnabled(false);
        assertFalse(Prefs.isIncognitoEnabled());
    }

    // Tests Incognito's setter
    @Test
    public void testIncognitoSetTrue() throws Throwable {
        Prefs.setIncognitoEnabled(true);
        assertTrue(Prefs.isIncognitoEnabled());
    }
}
