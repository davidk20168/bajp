package com.dicoding.jpsubdua.tvshow

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
class TvshowViewModelTest {
    private lateinit var viewModel : TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvshowEntity>>

    @Before
    fun setUp() {
        viewModel = TvshowViewModel(movieRepository)
    }

    @Test
    fun getTvshows() {
        val dummyTvshows = DataDummy.generateTvshows()
        val tvshows = MutableLiveData<List<TvshowEntity>>()
        tvshows.value = dummyTvshows

        `when`(movieRepository.getAllTvshows()).thenReturn(tvshows)
        val tvshowEntities = viewModel.getTvshows().value
        verify(movieRepository).getAllTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities?.size)

        viewModel.getTvshows().observeForever(observer)
        verify(observer).onChanged(dummyTvshows)
    }
}