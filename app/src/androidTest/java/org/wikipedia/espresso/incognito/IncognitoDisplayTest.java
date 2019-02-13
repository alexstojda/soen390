package org.wikipedia.espresso.incognito;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wikipedia.R;
import org.wikipedia.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IncognitoDisplayTest {

    private final int twoSeconds = 2000;
    private final int pointSevenSeconds = 700;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void incognitoDisplayTest() {

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(twoSeconds);

        skipOnboarding();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(pointSevenSeconds);

        tapOnHamburgerMenu();
        tapOnSettingsMenu();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(twoSeconds);

        toggleIncognitoModePreference();
        navigateOutOfSettingsMenu();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(pointSevenSeconds);

        ensureIncognitoDisplayIsPresent();
        navigateToAnArticle();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(twoSeconds);

        ensureIncognitoDisplayIsPresent();
        navigateOutOfArticle();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(pointSevenSeconds);

        tapOnHamburgerMenu();
        tapOnSettingsMenu();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(twoSeconds);

        toggleIncognitoModePreference();
        navigateOutOfSettingsMenu();
        ensureIncognitoDisplayIsAbsent();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(pointSevenSeconds);

        navigateBackToPreviousArticle();
        ensureIncognitoDisplayIsAbsent();
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void skipOnboarding() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.fragment_onboarding_skip_button), withText("Skip"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());
    }

    private void tapOnHamburgerMenu() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open"),
                        childAtPosition(
                                allOf(withId(R.id.single_fragment_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    private void tapOnSettingsMenu() {
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.main_drawer_settings_container),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_drawer_view),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());
    }

    private void navigateOutOfSettingsMenu() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    private void toggleIncognitoModePreference() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));
    }

    private void navigateToAnArticle() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.view_list_card_list),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                2)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        sleep(twoSeconds);

        recyclerView = onView(
                allOf(withId(R.id.view_news_fullscreen_link_card_list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    private void navigateOutOfArticle() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.page_toolbar),
                                        childAtPosition(
                                                withId(R.id.page_toolbar_container),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    private void navigateBackToPreviousArticle() {
        ViewInteraction floatingQueueView = onView(
                allOf(withId(R.id.floating_queue_view),
                        childAtPosition(
                                allOf(withId(R.id.fragment_main_coordinator),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        floatingQueueView.perform(click());
    }

    private void ensureIncognitoDisplayIsPresent() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.incognito_active_display_text), withText("INCOGNITO ACTIVE"),
                        childAtPosition(
                                allOf(withId(R.id.main_incognito_display),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));
    }

    private void ensureIncognitoDisplayIsAbsent() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.incognito_active_display_text), withText("INCOGNITO ACTIVE"),
                        childAtPosition(
                                allOf(withId(R.id.main_incognito_display),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(doesNotExist());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
