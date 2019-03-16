package org.wikipedia.thegame;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class GameClickHandlerTest {
    String endTitle;
    TextView textView;
    GameClickHandler gameClickHandler;

    @Before
    public void setUp() throws Exception {
        endTitle = "Canada";
        textView = mock(TextView.class);
        gameClickHandler = new GameClickHandler(endTitle, textView, null);
    }

    @Test
    public void whenIncorrectTitlePassed_returnFalse() {
        boolean result = gameClickHandler.verifyArticle("United States");
        assertFalse(result);
    }

    @Test
    public void whenCorrectTitlePassed_returnTrue() {
        boolean result = gameClickHandler.verifyArticle(endTitle);
        assertTrue(result);
    }
}