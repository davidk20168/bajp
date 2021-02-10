package com.dicoding.jpsubtiga

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.jpsubtiga.utils.DataDummy
import com.dicoding.jpsubtiga.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class FavoriteMovieActivityTest {
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
    fun loadMovieBookmarks() {
        onView(withId(R.id.rv_list_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText("Favorite Movie")).perform(click())
        onView(withId(R.id.rv_favorite_list_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_list_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.txt_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_movie_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.txt_movie_category)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_movie_category)).check(matches(withText("Category : " + dummyMovie[0].category)))
        onView(withId(R.id.txt_movie_synopsis_value)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_movie_synopsis_value)).check(matches(withText(dummyMovie[0].synopsis)))
        onView(withId(R.id.img_item_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

}