package com.dicoding.jpsubsatu.tvshow

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvshowViewModelTest {
    private lateinit var viewModel : TvshowViewModel

    @Before
    fun setUp() {
        viewModel = TvshowViewModel()
    }

    @Test
    fun getTvshows() {
        val tvshowEntities = viewModel.getTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities.size)
    }
}