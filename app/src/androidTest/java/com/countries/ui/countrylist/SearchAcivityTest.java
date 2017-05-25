package com.countries.ui.countrylist;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.countries.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchAcivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchAcivityTest() {
        ViewInteraction appCompatImageButton = onView(
allOf(withId(R.id.search_icon_button),
withParent(allOf(withId(R.id.toolbar),
withParent(withId(R.id.appbar)))),
isDisplayed()));
        appCompatImageButton.perform(click());
        
        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.search_edit_text),
withParent(withId(R.id.toolbar)),
isDisplayed()));
        appCompatEditText.perform(replaceText("Swe"), closeSoftKeyboard());
        
        ViewInteraction recyclerView = onView(
allOf(withId(R.id.countries_recyclerview), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        
        ViewInteraction textView = onView(
allOf(withId(R.id.capital), withText("Stockholm"),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
1),
0),
isDisplayed()));
        textView.check(matches(withText("Stockholm")));
        
        ViewInteraction textView2 = onView(
allOf(withId(R.id.language), withText("Swedish"),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
1),
0),
isDisplayed()));
        textView2.check(matches(withText("Swedish")));
        
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
