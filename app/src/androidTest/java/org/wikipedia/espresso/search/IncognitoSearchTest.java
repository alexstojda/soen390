package org.wikipedia.espresso.search;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wikipedia.R;
import org.wikipedia.main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class IncognitoSearchTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void incognitoSearchTest() {
        clickSearchContainer(R.id.search_container, R.id.fragment_feed_feed, 0);
        performSearch("test");
        clickOnArticle(R.id.page_toolbar, R.id.page_toolbar_container, 0);
        clickSearchContainer(R.id.search_container, R.id.fragment_feed_feed, 0);
        checkRecentSearch();

        ViewInteraction appCompatImageButton4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.search_toolbar),
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0)),
                        0),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Open"),
                        childAtPosition(
                                allOf(withId(R.id.single_fragment_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        clickSearchContainer(R.id.main_drawer_settings_container, R.id.navigation_drawer_view, 1);

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));
        clickOnArticle(R.id.action_bar, R.id.action_bar_container, 1);
        clickSearchContainer(R.id.search_container, R.id.fragment_feed_feed, 0);
        performSearch("android");
        clickOnArticle(R.id.page_toolbar, R.id.page_toolbar_container, 0);
        clickSearchContainer(R.id.search_container, R.id.fragment_feed_feed, 0);
        checkRecentSearch();
    }

    private void clickOnArticle(int p, int p2, int i) {
        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(p),
                                        childAtPosition(
                                                withId(p2),
                                                0)),
                                i),
                        isDisplayed()));
        appCompatImageButton3.perform(click());
    }

    private void checkRecentSearch() {
        ViewInteraction textView = onView(
                allOf(withText("test"),
                        childAtPosition(
                                allOf(withId(R.id.recent_searches_list),
                                        childAtPosition(
                                                withId(R.id.recent_searches),
                                                2)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("test")));
    }

    private void performSearch(String test) {
        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText(test), closeSoftKeyboard());

        DataInteraction constraintLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.search_results_list),
                        childAtPosition(
                                withId(R.id.search_results_container),
                                1)))
                .atPosition(0);
        constraintLayout.perform(click());
    }

    private void clickSearchContainer(int p, int p2, int i) {
        ViewInteraction linearLayout3 = onView(
                allOf(withId(p),
                        childAtPosition(
                                childAtPosition(
                                        withId(p2),
                                        0),
                                i),
                        isDisplayed()));
        linearLayout3.perform(click());
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
