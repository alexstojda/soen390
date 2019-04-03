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
        void updateArtist(String artistName);
    }

    static final class BroadcastTypes {
        static final String METADATA_CHANGED = "com.spotify.music.metadatachanged";
    }

    private Callback callback;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(BroadcastTypes.METADATA_CHANGED)) {
            String artistName = intent.getStringExtra("artist");
            //Leaving these here in case we need them
            String trackId = intent.getStringExtra("id");
            String albumName = intent.getStringExtra("album");
            String trackName = intent.getStringExtra("track");

            callback.updateArtist(artistName);
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
