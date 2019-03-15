package org.wikipedia.thegame;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.wikipedia.util.log.L;

public class GameClickHandler {
    private int gameScore;
    private String endTitle;
    private TextView textView;
    private Context context;

    public GameClickHandler(String endTitle, TextView textView, Context context) {
        this.textView = textView;
        this.context = context;
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
            System.err.println("YOU WON (lost) THE GAME WOOOOOOOOO");
            System.err.println("It took you: " + gameScore + " tries");
            Toast toast = Toast.makeText(context, "You have won The Game.", 3000);
            toast.show();


            return true;
        }
        return false;
    }
}
