package org.wikipedia.thegame;

import android.widget.TextView;
import android.widget.Toast;

import org.wikipedia.page.PageFragment;
import org.wikipedia.util.log.L;

public class GameClickHandler {

    private int gameScore;
    private String endTitle;
    private TextView textView;
    private PageFragment pageFragment;

    public GameClickHandler(String endTitle, TextView textView, PageFragment pageFragment) {
        this.textView = textView;
        this.pageFragment = pageFragment;
        this.gameScore = 0;
        this.endTitle = endTitle;
        modifyText();
    }

    public void incrementScore() {
        gameScore++;
        modifyText();
    }

    private void modifyText() {
        textView.setText("Score: " + gameScore);
    }

    public boolean verifyArticle(String title) {

        L.e("passed title: " + title);
        if (title.equals(endTitle)) {
            if (pageFragment != null) {
                pageFragment.endGame();
                Toast toast = Toast.makeText(pageFragment.getContext(), "Congratulations! You have "
                                + "won The Game. It took you " + gameScore + " clicks.",
                        Toast.LENGTH_LONG);
                toast.show();

            }
            return true;
        }
        return false;
    }

    public int getGameScore() {
        return gameScore;
    }
}
