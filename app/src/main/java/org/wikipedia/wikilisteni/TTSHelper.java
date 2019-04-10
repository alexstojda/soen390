package org.wikipedia.wikilisteni;

import android.speech.tts.TextToSpeech;

import org.wikipedia.page.PageSection;

import java.util.Iterator;
import java.util.List;

public class TTSHelper {

    public static final String SECTION_END_TAG = "sectionEnd";

    private TextToSpeech tts;
    private Iterator<PageSection> pageSectionIterator;

    public TTSHelper(TextToSpeech tts) {
        this.tts = tts;
    }

    public void start(List<PageSection> pageSections) {
        this.pageSectionIterator = pageSections.iterator();
        tts.setOnUtteranceProgressListener(new TTSProgressTracker(this));
        playNextSection();
    }

    public void playNextSection() {
        tts.stop();
        if (!pageSectionIterator.hasNext()) {
            return;
        }

        PageSection sectionToPlay = pageSectionIterator.next();
        tts.playSilentUtterance(500, TextToSpeech.QUEUE_FLUSH, "");
        tts.speak(sectionToPlay.getTitle(), TextToSpeech.QUEUE_ADD, null, "");
        tts.playSilentUtterance(500, TextToSpeech.QUEUE_ADD, "");
        tts.speak(sectionToPlay.getContents(), TextToSpeech.QUEUE_ADD, null, SECTION_END_TAG);

    }

    public void stop() {
        tts.stop();
    }
}
