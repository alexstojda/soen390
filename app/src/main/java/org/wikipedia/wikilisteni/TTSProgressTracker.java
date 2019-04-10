package org.wikipedia.wikilisteni;

import android.speech.tts.UtteranceProgressListener;

public class TTSProgressTracker extends UtteranceProgressListener {

    TTSHelper ttsHelper;

    public TTSProgressTracker(TTSHelper ttsHelper) {
        this.ttsHelper = ttsHelper;
    }

    @Override
    public void onDone(String utteranceId) {

        // This callback gets called every time an utterance is done.
        // For each section, there are 4 utterances, pause, title, pause, content.
        // The content will have the ID "sectionEnd", when this utterance is done,
        // we want to start playing the next section.
        // This callback does not fire when TextToSpeech::stop() is called.
        if (TTSHelper.SECTION_END_TAG.equals(utteranceId)) {
            this.ttsHelper.playNextSection();
        }
    }

    @Override
    public void onStart(String utteranceId) { }

    @Override
    public void onError(String utteranceId) { }
}
