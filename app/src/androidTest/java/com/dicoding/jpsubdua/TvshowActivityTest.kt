package com.dicoding.jpsubdua

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.jpsubdua.utils.DataDummy
import com.dicoding.jpsubdua.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class TvshowActivityTest {
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
        onView(withId(R.id.rv_list_tvshow)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_list_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvshow.size))
    }

    @Test
    fun loadDetailTvshow() {
        onView(withId(R.id.rv_list_tvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))

        onView(withId(R.id.rv_list_tvshow))
            .perform(ViewActions.swipeUp())

        onView(withId(R.id.rv_list_tvshow))
            .perform(ViewActions.swipeUp())

        onView(withId(R.id.rv_list_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )

        onView(withId(R.id.txt_tvshow_title))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.txt_tvshow_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvshow[2].title)))
        onView(withId(R.id.txt_tvshow_category))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.txt_tvshow_category))
            .check(ViewAssertions.matches(ViewMatchers.withText("Category : " + dummyTvshow[2].category)))
        onView(withId(R.id.txt_tvshow_synopsis_value))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.txt_tvshow_synopsis_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvshow[2].synopsis)))
        onView(withId(R.id.img_item_tvshow))
            .check(ViewAssertions.matches(isDisplayed()))
        pressBack()
    }

}