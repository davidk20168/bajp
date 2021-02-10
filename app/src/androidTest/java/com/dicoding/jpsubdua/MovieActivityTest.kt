package com.dicoding.jpsubdua

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.jpsubdua.utils.DataDummy
import com.dicoding.jpsubdua.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class MovieActivityTest {
    private val dummyMovie = DataDummy.generateMovies()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_list_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_list_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.rv_list_movie)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.txt_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_movie_title)).check(matches(withText(dummyMovie[2].title)))
        onView(withId(R.id.txt_movie_category)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_movie_category)).check(matches(withText("Category : " + dummyMovie[2].category)))
        onView(withId(R.id.txt_movie_synopsis_value)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_movie_synopsis_value)).check(matches(withText(dummyMovie[2].synopsis)))
        onView(withId(R.id.img_item_movie)).check(matches(isDisplayed()))
        pressBack()
    }



}