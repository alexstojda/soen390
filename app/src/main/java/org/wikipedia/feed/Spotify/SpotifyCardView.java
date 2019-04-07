package org.wikipedia.feed.Spotify;

import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageButton;
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
        implements ItemTouchHelperSwipeAdapter.SwipeableView {

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

    private boolean songIsPlaying = false;
    private Context context;

    public SpotifyCardView(Context context) {
        super(context);
        this.context = context;

        inflate(context, R.layout.view_spotify_card, this);
        ButterKnife.bind(this);

        SpotifyRemote spotifyRemote = new SpotifyRemote(context);
        skipNext.setOnClickListener(v -> {
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
                Log.e("MainFragment", "User resumed song");
                songIsPlaying = true;
                playButton.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.ic_pause_black_24dp));
            } else {
                Log.e("MainFragment", "User paused song");
                songIsPlaying = false;
                playButton.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.ic_play_arrow_black_24dp));
            }
        }
    }

}
