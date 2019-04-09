package org.wikipedia.wikilisteni;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import org.wikipedia.page.PageSection;

import java.util.List;

public class TTSHelper {

    private TextToSpeech tts;
    private List<PageSection> pageSections;

    public TTSHelper(TextToSpeech tts) {
        this.tts = tts;
    }

    public void setPageSections(List<PageSection> pageSections) {
        this.pageSections = pageSections;
    }

    public void startTTS() {

    }

    public void skipSectionTTS() {

    }

    public void stopTTS() {

    }

    public UtteranceProgressListener getProgressTracker() {
        return new TTSProgressTracker(pageSections, tts);
    }

}
