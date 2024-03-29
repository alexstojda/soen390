package org.wikipedia.wikilisteni;

import android.speech.tts.TextToSpeech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.wikipedia.page.PageSection;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TTSTest {

    private TextToSpeech tts;
    private TTSHelper ttsHelper;

    public TTSTest() {
        tts = mock(TextToSpeech.class);
        ttsHelper = new TTSHelper(tts);
        ttsHelper.setFinishCallback(new TTSHelper.TTSFinishedCallback() {
            @Override
            public void finished() { }
        });
    }

    @Test
    public void checkDoesntPlayOnEmpty() {
        ttsHelper.start(new ArrayList<>());
        assertFalse(ttsHelper.isPlaying());
    }

    @Test
    public void checkDoesntPlayNextOnEmpty() {
        ArrayList<PageSection> textList = new ArrayList<PageSection>();
        textList.add(new PageSection("Hello", "Good day to you"));
        ttsHelper.start(textList);
        ttsHelper.playNextSection();
        assertFalse(ttsHelper.isPlaying());
    }

}
