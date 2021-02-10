package com.dicoding.jpsubsatu.tvshowdetail

import com.dicoding.jpsubsatu.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailTvshowViewModelTest {
    private lateinit var viewModel: DetailTvshowViewModel
    private val dummyTvshow = DataDummy.generateTvshows()[0]
    private val tvshowId = dummyTvshow.tvshowId

    @Before
    fun setUp() {
        viewModel = DetailTvshowViewModel()
        viewModel.setSelectedTvshow(tvshowId)
    }

    @Test
    fun getTvshow() {
        viewModel.setSelectedTvshow(dummyTvshow.tvshowId)
        val tvshowEntity = viewModel.getTvshow()
        assertNotNull(tvshowEntity)
        assertEquals(dummyTvshow.tvshowId, tvshowEntity.tvshowId)
        assertEquals(dummyTvshow.title, tvshowEntity.title)
        assertEquals(dummyTvshow.category, tvshowEntity.category)
        assertEquals(dummyTvshow.synopsis, tvshowEntity.synopsis)
        assertEquals(dummyTvshow.imagePoster, tvshowEntity.imagePoster)
    }
}