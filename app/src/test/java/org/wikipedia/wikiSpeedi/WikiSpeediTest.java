package org.wikipedia.wikispeedi;

import android.widget.SeekBar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class WikiSpeedi {
    private WikiSpeediDialog wikiSpeedi;
    private final ShadowApplication shadowApplication = ShadowApplication.getInstance();
    private String testString;

    @Before
    public void setUp() {
        testString = "this is a test";
        wikiSpeedi = new WikiSpeediDialog(shadowApplication.getApplicationContext(), testString);
    }

    @Test
    public void testParse() {
        Assert.assertEquals(wikiSpeedi.getSelectedText(), Arrays.asList(testString.split(
                "\\s")));
    }

    @Test
    public void testIsNotRunningbyDefault() {
        assertEquals(0, wikiSpeedi.getIndex());
        assertEquals("- start -", wikiSpeedi.getSprintText());
        assertFalse(wikiSpeedi.getIsRunning());
    }

    @Test
    public void testReset() {
        wikiSpeedi.setIsRunning(true); //this will begin the sprint
        wikiSpeedi.resetSprint(); // this should stop the sprint and reset the values to default
        assertFalse(wikiSpeedi.getIsRunning());
        assertEquals("- start -", wikiSpeedi.getSprintText());
        assertEquals(0, wikiSpeedi.getIndex());
    }

    @Test
    public void testIsRunning() {
        wikiSpeedi.setIsRunning(true);
        assertTrue(wikiSpeedi.getIsRunning());
    }
}
