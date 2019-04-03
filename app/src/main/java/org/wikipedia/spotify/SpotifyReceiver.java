package org.wikipedia.spotify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
 *   The SpotifyReceiver class extends the Android BroadcastReceiver class, and is used
 *   to get notified of changes from the Spotify player. The inner callback interface is
 *   where you can define what methods the fragment/activity/view will have to implement
 *   when the song is changed on Spotify. If you want to actually perform actions on the
 *   Spotify API, use SpotifyRemote instead.
 */
public class SpotifyReceiver extends BroadcastReceiver {

    public interface Callback {
        void updateCurrentlyPlaying(String track, String album, String artist);

        void songStartedPlaying(boolean isPlaying);
    }

    static final class BroadcastTypes {
        static final String SPOTIFY_PACKAGE = "com.spotify.music";
        static final String PLAYBACK_STATE_CHANGED = SPOTIFY_PACKAGE + ".playbackstatechanged";
        static final String METADATA_CHANGED = SPOTIFY_PACKAGE + ".metadatachanged";
    }

    private Callback callback;

    private String currentTrack;
    private String currentAlbum;
    private String currentArtist;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(BroadcastTypes.METADATA_CHANGED)) {
            currentArtist = intent.getStringExtra("artist");
            currentAlbum = intent.getStringExtra("album");
            currentTrack = intent.getStringExtra("track");
            callback.updateCurrentlyPlaying(currentTrack, currentAlbum, currentArtist);

        } else if (action.equals(BroadcastTypes.PLAYBACK_STATE_CHANGED)) {
            if (intent.getBooleanExtra("playing", false)) {
                callback.songStartedPlaying(true);
                callback.updateCurrentlyPlaying(currentTrack, currentAlbum, currentArtist);
            } else {
                callback.songStartedPlaying(false);
                callback.updateCurrentlyPlaying("", "", "");
            }
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
