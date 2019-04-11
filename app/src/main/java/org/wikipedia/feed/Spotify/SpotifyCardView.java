package org.wikipedia.feed.Spotify;

import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.wikipedia.R;
import org.wikipedia.feed.model.Card;
import org.wikipedia.feed.view.DefaultFeedCardView;
import org.wikipedia.spotify.SpotifyReceiver;
import org.wikipedia.spotify.SpotifyRemote;
import org.wikipedia.views.ItemTouchHelperSwipeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SpotifyCardView<T extends Card> extends DefaultFeedCardView<T>
        implements ItemTouchHelperSwipeAdapter.SwipeableView, SpotifyRemote.Callback {

    @BindView(R.id.artist_name)
    TextView artistName;
    @BindView(R.id.album_name)
    TextView albumName;
    @BindView(R.id.song_name)
    TextView songName;
    @BindView(R.id.skip_previous)
    ImageButton skipPrevious;
    @BindView(R.id.skip_next)
    ImageButton skipNext;
    @BindView(R.id.play)
    ImageButton playButton;
    @BindView(R.id.view_artist_button)
    Button viewArtistButton;
    @BindView(R.id.connect_to_spotify_button)
    Button connectButton;
    @BindView(R.id.spotify_loading_spinner)
    ProgressBar loadingSpinner;

    private boolean songIsPlaying = false;
    private Context context;
    private SpotifyRemote spotifyRemote;

    public SpotifyCardView(Context context) {
        super(context);
        this.context = context;
        inflate(context, R.layout.view_spotify_card, this);
        ButterKnife.bind(this);

        artistName.setVisibility(GONE);
        albumName.setVisibility(GONE);
        songName.setVisibility(GONE);
        skipNext.setVisibility(GONE);
        skipPrevious.setVisibility(GONE);
        playButton.setVisibility(GONE);
        viewArtistButton.setVisibility(GONE);
        loadingSpinner.setVisibility(GONE);

        spotifyRemote = new SpotifyRemote(context);
        skipNext.setOnClickListener(v -> {
            loadingSpinner.setVisibility(VISIBLE);
            spotifyRemote.skipNext();
        });
        skipPrevious.setOnClickListener(v -> spotifyRemote.skipPrevious());
        playButton.setOnClickListener(v -> {
            if (songIsPlaying) {
                spotifyRemote.pause();
                songIsPlaying = false;
            } else {
                spotifyRemote.resume();
                songIsPlaying = true;
            }
        });

        connectButton.setOnClickListener(v -> {
            spotifyRemote.connectToSpotify(this);
            connectButton.setVisibility(GONE);
            loadingSpinner.setVisibility(VISIBLE);
        });

        SpotifyReceiver spotifyReceiver = new SpotifyReceiver();
        context.registerReceiver(spotifyReceiver, getIntentFilter());
        SpotifyCallback spotifyCallback = new SpotifyCallback();
        spotifyReceiver.setCallback(spotifyCallback);
    }

    @NonNull
    private IntentFilter getIntentFilter() {
        IntentFilter spotifyIntentFilter = new IntentFilter();
        spotifyIntentFilter.addAction("com.spotify.music.metadatachanged");
        spotifyIntentFilter.addAction("com.spotify.music.playbackstatechanged");
        return spotifyIntentFilter;
    }

    @Override
    public void onSuccess() {
        artistName.setVisibility(VISIBLE);
        albumName.setVisibility(VISIBLE);
        songName.setVisibility(VISIBLE);
        skipNext.setVisibility(VISIBLE);
        skipPrevious.setVisibility(VISIBLE);
        playButton.setVisibility(VISIBLE);
        viewArtistButton.setVisibility(VISIBLE);
        connectButton.setVisibility(GONE);
        loadingSpinner.setVisibility(GONE);
    }

    @Override
    public void onFailure() {
        spotifyRemote = null;
        connectButton.setVisibility(VISIBLE);
        loadingSpinner.setVisibility(GONE);
    }

    private class SpotifyCallback implements SpotifyReceiver.Callback {

        @Override
        public void updateCurrentlyPlaying(String track, String album, String artist) {
            artistName.setText(artist);
            songName.setText(track);
            albumName.setText(album);
        }

        @Override
        public void songStartedPlaying(boolean isPlaying) {
            if (isPlaying) {
                Log.e("SpotifyCardView", "User resumed song");
                songIsPlaying = true;
                findViewById(R.id.currently_playing_layout).setVisibility(View.VISIBLE);
                playButton.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.ic_pause_black_24dp));
            } else {
                Log.e("SpotifyCardView", "User paused song");
                songIsPlaying = false;
                findViewById(R.id.currently_playing_layout).setVisibility(View.GONE);
                playButton.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.ic_play_arrow_black_24dp));
            }
        }
    }

}
