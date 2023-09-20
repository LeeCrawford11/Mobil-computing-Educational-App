package com.example.wordexplorer

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserStatisticsActivityGUITest {
    @Rule
    @JvmField
    var rule = ActivityScenarioRule(UserStatisticsActivity::class.java)


    // check if the TextViews for learned flashcards count are displaying the correct text when activity is started

    @Test
    fun checkFixedTexts() {
        onView(withId(R.id.easy_flashcards_count)).check(matches(withText("Easy flashcards learned: 0")))
        onView(withId(R.id.medium_flashcards_count)).check(matches(withText("Medium flashcards learned: 0")))
        onView(withId(R.id.hard_flashcards_count)).check(matches(withText("Hard flashcards learned: 0")))
    }

    // check if TextViews for learned flashcards count are visible when activity is started
    @Test
    fun checkVisibilityOnStart() {
        onView(withId(R.id.easy_flashcards_count)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.medium_flashcards_count)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.hard_flashcards_count)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}
