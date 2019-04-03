package org.wikipedia.spotify;

import android.support.v4.app.Fragment;
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

    private static final String CLIENT_ID = "89625714de2848f48e048a3f628968d5";
    private static final String REDIRECT_URI = "testtest://redirect";
    private SpotifyAppRemote mSpotifyAppRemote;
    private PlayerApi mPlayerApi;


    public SpotifyRemote(Fragment fragment) {
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(fragment.getContext(), connectionParams,
                new Connector.ConnectionListener() {
                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        mPlayerApi = mSpotifyAppRemote.getPlayerApi();
                        Log.d("SpotifyRemote", "Connected to Spotify!");
                        playSong("spotify:track:3cfOd4CMv2snFaKAnMdnvK");
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("SpotifyRemote", throwable.getMessage(), throwable);
                    }
                });
    }

    public void playSong(String uri) {
        mPlayerApi.play(uri);
        Log.d("SpotifyRemote", "Song is playing");
    }

    public void play() {
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
