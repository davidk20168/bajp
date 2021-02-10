package com.dicoding.jpsubdua.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jpsubdua.data.TvshowEntity
import com.dicoding.jpsubdua.data.source.MovieRepository
import com.dicoding.jpsubdua.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvshowViewModelTest {
    private lateinit var viewModel: DetailTvshowViewModel
    private val dummyTvshow = DataDummy.generateTvshows()[0]
    private val tvshowId = dummyTvshow.tvshowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvshowsObserver: Observer<TvshowEntity>

    @Before
    fun setUp() {
        viewModel = DetailTvshowViewModel(movieRepository)
        viewModel.setSelectedTvshow(tvshowId)
    }

    @Test
    fun getTvshow() {
        val tvshow = MutableLiveData<TvshowEntity>()
        tvshow.value = dummyTvshow

        `when`(movieRepository.getContentTvshow(dummyTvshow.tvshowId)).thenReturn(tvshow)
        val tvshowEntity = viewModel.getTvshow().value as TvshowEntity
        verify(movieRepository).getContentTvshow(dummyTvshow.tvshowId)
        assertNotNull(tvshowEntity)
        assertEquals(dummyTvshow.tvshowId, tvshowEntity.tvshowId)
        assertEquals(dummyTvshow.title, tvshowEntity.title)
        assertEquals(dummyTvshow.category, tvshowEntity.category)
        assertEquals(dummyTvshow.synopsis, tvshowEntity.synopsis)
        assertEquals(dummyTvshow.imagePoster, tvshowEntity.imagePoster)

        viewModel.getTvshow().observeForever(tvshowsObserver)
        verify(tvshowsObserver).onChanged(dummyTvshow)
    }
}