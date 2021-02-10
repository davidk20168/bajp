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

class FavoriteTvshowActivityTest {
    private val dummyTvshow = DataDummy.generateTvshows()

    @Before
    fun setupTvshow() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.view_pager))
                .perform(ViewActions.swipeLeft())
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }


    @Test
    fun loadTvshow() {
        onView(withId(R.id.rv_list_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvshow.size))
    }

    @Test
    fun loadTvshowBookmark()
    {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_list_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText("Favorite TvShow")).perform(click())
        onView(withId(R.id.rv_favorite_list_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_list_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.txt_tvshow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_tvshow_title)).check(matches(withText(dummyTvshow[0].title)))
        onView(withId(R.id.txt_tvshow_category)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_tvshow_category)).check(matches(withText("Category : " + dummyTvshow[0].category)))
        onView(withId(R.id.txt_tvshow_synopsis_value)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_tvshow_synopsis_value)).check(matches(withText(dummyTvshow[0].synopsis)))
        onView(withId(R.id.img_item_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

}