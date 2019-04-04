package org.wikipedia.wikiSpeedi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class wikispeedi {
    String testString;
    WikiSpeediDialog wikiSpeedi;
    final ShadowApplication shadowApplication = ShadowApplication.getInstance();

    @Before
    public void setUp() throws Exception {

        testString = "this is a test";
        wikiSpeedi = new WikiSpeediDialog(shadowApplication.getApplicationContext(), testString);
    }

    @Test
    public void testParse() {
        //Here we can test if the string gets parsed properly into an array of strings
    }

    @Test
    public void testIsNotRunningbyDefault() {
        assertEquals(0, wikiSpeedi.getPlaceHolder());
        assertEquals("- start -", wikiSpeedi.getSprintText());
        assertFalse(wikiSpeedi.getIsRunning());
    }

    @Test
    public void testReset() {
        wikiSpeedi.setIsRunning(true); //this will begin the sprint
        wikiSpeedi.resetSprint(); // this should stop the sprint and reset the values to default
        assertFalse(wikiSpeedi.getIsRunning());
        assertEquals("- start -", wikiSpeedi.getSprintText());
        assertEquals(0, wikiSpeedi.getPlaceHolder());
    }

    @Test
    public void testIsRunning() {
        wikiSpeedi.setIsRunning(true);
        assertTrue(wikiSpeedi.getIsRunning());
    }
}