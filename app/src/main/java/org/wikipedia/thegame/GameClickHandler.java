package org.wikipedia.thegame;

import android.widget.TextView;

import org.wikipedia.util.log.L;

public class GameClickHandler {
    private int gameScore;
    private String endTitle;
    private TextView textView;

    public GameClickHandler(String endTitle, TextView textView) {
        this.textView = textView;
        this.gameScore = 0;
        this.endTitle = endTitle;
    }

    public void incrementScore() {
        gameScore++;
        textView.setText("Score: " + gameScore);
    }

    public void verifyArticle(String title) {

        L.e("passed title: " + title);
        if (title.equals(endTitle)) {
            System.err.println("YOU WON (lost) THE GAME WOOOOOOOOO");
            System.err.println("It took you: " + gameScore + " tries");
        }
    }
}
