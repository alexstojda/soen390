package org.wikipedia.feed.Spotify;

import android.support.annotation.NonNull;

import org.wikipedia.feed.model.Card;
import org.wikipedia.feed.model.CardType;

public class SpotifyCard extends Card {
    @NonNull
    @Override public CardType type() {
        return CardType.SPOTIFY;
    }
}
