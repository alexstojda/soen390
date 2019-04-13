package org.wikipedia.wikilisteni;

import android.speech.tts.TextToSpeech;

import org.wikipedia.page.PageSection;

import java.util.Iterator;
import java.util.List;

public class TTSHelper {

    public static final String SECTION_END_TAG = "sectionEnd";

    private TextToSpeech tts;
    private boolean isPlaying;
    private Iterator<PageSection> pageSectionIterator;
    private TTSFinishedCallback callback;

    public TTSHelper(TextToSpeech tts) {
        this.tts = tts;
        isPlaying = false;
    }

    public void setFinishCallback(TTSFinishedCallback callback) {
        this.callback = callback;
    }

    public void start(List<PageSection> pageSections) {
        this.pageSectionIterator = pageSections.iterator();
        tts.setOnUtteranceProgressListener(new TTSProgressTracker(this));
        playNextSection();
        isPlaying = true;
    }

    public void playNextSection() {
        tts.stop();
        if (!pageSectionIterator.hasNext()) {
            callback.finished();
            isPlaying = false;
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
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public interface TTSFinishedCallback {
        void finished();
    }
}
