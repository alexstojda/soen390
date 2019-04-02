package org.wikipedia.feed.Spotify;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import org.wikipedia.R;
import org.wikipedia.feed.view.StaticCardView;

public class SpotifyCardView extends StaticCardView<SpotifyCard> {

    public SpotifyCardView(@NonNull Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTransitionName(getString(R.string.transition_random_activity));
        }
    }

    @Override public void setCard(@NonNull SpotifyCard card) {
        super.setCard(card);
        setTitle("Spotify Artist");
        setSubtitle("Get information on current artist");
        setIcon(R.drawable.ic_casino_accent50_24dp);
        setContainerBackground(R.color.green30);
      //  setAction(R.drawable.ic_casino_accent50_24dp, R.string.view_random_card_action);
    }
}
