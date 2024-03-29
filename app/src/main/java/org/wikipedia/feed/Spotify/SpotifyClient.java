package org.wikipedia.feed.Spotify;

import org.wikipedia.dataclient.WikiSite;
import org.wikipedia.feed.dataclient.DummyClient;
import org.wikipedia.feed.model.Card;

public class SpotifyClient extends DummyClient {
    @Override public Card getNewCard(WikiSite wiki) {
        return new SpotifyCard();
    }
}
