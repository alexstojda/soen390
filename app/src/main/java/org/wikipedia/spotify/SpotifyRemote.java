package org.wikipedia.spotify;

import android.content.Context;
import android.util.Log;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;

/*
 *   This class will allow you to control the Spotify player (play, pause, seek, skip, etc).
 *   However, this is not where you will find the title, artist, album of the current song.
 *   Use SpotifyReceiver instead.
 */
public class SpotifyRemote {

    public interface Callback {
        void onSuccess();

        void onFailure();
    }

    private static final String CLIENT_ID = "89625714de2848f48e048a3f628968d5";
    private static final String REDIRECT_URI = "testtest://redirect";
    private SpotifyAppRemote mSpotifyAppRemote;
    private PlayerApi mPlayerApi;
    private Context context;


    public SpotifyRemote(Context context, Callback callback) {
        this.context = context;
        connectToSpotify(callback, false);
    }

    public void connectToSpotify(Callback callback, boolean showAuthView) {

        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID).setRedirectUri(REDIRECT_URI).
                        showAuthView(showAuthView).build();

        SpotifyAppRemote.connect(context, connectionParams, new Connector.ConnectionListener() {
            @Override
            public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                mSpotifyAppRemote = spotifyAppRemote;
                mPlayerApi = mSpotifyAppRemote.getPlayerApi();
                Log.d("SpotifyRemote", "Connected to Spotify!");
                callback.onSuccess();
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onFailure();
            }
        });
    }

    public void resume() {
        mPlayerApi.resume();
    }

    public void pause() {
        mPlayerApi.pause();
    }

    public void skipNext() {
        mPlayerApi.skipNext();
    }

    public void skipPrevious() {
        mPlayerApi.skipPrevious();
    }
}
