package org.wikipedia.feed.Spotify;

import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.wikipedia.R;
import org.wikipedia.feed.model.Card;
import org.wikipedia.feed.view.DefaultFeedCardView;
import org.wikipedia.history.HistoryEntry;
import org.wikipedia.page.PageActivity;
import org.wikipedia.search.SearchHandler;
import org.wikipedia.search.SearchResult;
import org.wikipedia.spotify.SpotifyReceiver;
import org.wikipedia.spotify.SpotifyRemote;
import org.wikipedia.views.ItemTouchHelperSwipeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpotifyCardView<T extends Card> extends DefaultFeedCardView<T>
        implements ItemTouchHelperSwipeAdapter.SwipeableView, SearchHandler.Callback {

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
    TextView connectButton;
    @BindView(R.id.currently_playing_layout)
    RelativeLayout currentlyPlayingLayout;
    @BindView(R.id.spotify_controls)
    RelativeLayout spotifyControls;
    @BindView(R.id.spotify_connect)
    RelativeLayout spotifyConnect;

    private boolean songIsPlaying = false;
    private Context context;
    private SpotifyRemote spotifyRemote;
    private SpotifyRemote.Callback connectionCallback;
    private SearchHandler searchHandler = new SearchHandler(this);

    public SpotifyCardView(Context context) {
        super(context);
        this.context = context;
        inflate(context, R.layout.view_spotify_card, this);
        ButterKnife.bind(this);

        spotifyControls.setVisibility(GONE);

        connectionCallback = new SpotifyRemote.Callback() {
            @Override
            public void onSuccess() {
                spotifyControls.setVisibility(VISIBLE);
                if (songIsPlaying) {
                    currentlyPlayingLayout.setVisibility(VISIBLE);
                }
                spotifyConnect.setVisibility(GONE);
            }

            @Override
            public void onFailure() {
                currentlyPlayingLayout.setVisibility(GONE);
                spotifyControls.setVisibility(GONE);
                spotifyConnect.setVisibility(VISIBLE);
            }
        };

        spotifyRemote = new SpotifyRemote(context, connectionCallback);

        connectButton.setOnClickListener(v -> spotifyRemote.connectToSpotify(connectionCallback, true));

        viewArtistButton.setOnClickListener(v -> {
            searchHandler.searchForArtist(artistName.getText().toString());
        });

        setupRemoteButtons();
        setUpReceiverCallback(context);
    }

    private void setUpReceiverCallback(Context context) {
        SpotifyReceiver spotifyReceiver = new SpotifyReceiver();
        context.registerReceiver(spotifyReceiver, getIntentFilter());
        ReceiverCallback receiverCallback = new ReceiverCallback();
        spotifyReceiver.setCallback(receiverCallback);
    }

    private void setupRemoteButtons() {
        skipNext.setOnClickListener(v -> spotifyRemote.skipNext());
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

    @NonNull
    private IntentFilter getIntentFilter() {
        IntentFilter spotifyIntentFilter = new IntentFilter();
        spotifyIntentFilter.addAction("com.spotify.music.metadatachanged");
        spotifyIntentFilter.addAction("com.spotify.music.playbackstatechanged");
        return spotifyIntentFilter;
    }

    @Override
    public void getArtist(SearchResult searchResult) {
        HistoryEntry historyEntry = new HistoryEntry(searchResult.getPageTitle(), HistoryEntry.SOURCE_SEARCH);
        context.startActivity(PageActivity.newIntentForNewTab(context, historyEntry,
                searchResult.getPageTitle()));
    }

    private class ReceiverCallback implements SpotifyReceiver.Callback {

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
                if (spotifyConnect.getVisibility() == GONE) {
                    currentlyPlayingLayout.setVisibility(View.VISIBLE);
                }
                playButton.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.ic_pause_black_24dp));
            } else {
                Log.e("SpotifyCardView", "User paused song");
                songIsPlaying = false;
                currentlyPlayingLayout.setVisibility(View.GONE);
                playButton.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.ic_play_arrow_black_24dp));
            }
        }
    }

}
