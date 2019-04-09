package org.wikipedia.wikilisteni;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import org.wikipedia.page.PageSection;

import java.util.List;

public class TTSProgressTracker extends UtteranceProgressListener {

    TextToSpeech tts;
    List<PageSection> pageSections;
    int currentSectionIndex;

    public TTSProgressTracker(List<PageSection> pageSections, TextToSpeech tts) {
        this.pageSections = pageSections;
        this.tts = tts;
        currentSectionIndex = 0;
    }

    public void onSkipSection() {

    }

    @Override
    public void onDone(String utteranceId) {

    }

    @Override
    public void onStart(String utteranceId) { }

    @Override
    public void onError(String utteranceId) { }
}
