package org.wikipedia.feed.Spotify;

import org.wikipedia.R;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.TextView;

import org.wikipedia.feed.model.Card;
import org.wikipedia.feed.view.DefaultFeedCardView;

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

    public SpotifyCardView(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_spotify_card, this);
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
    }

}
