package com.dicoding.jpsubtiga.favoritetvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvshowViewModelTest {
    private lateinit var viewModel: FavoriteTvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvshowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvshowEntity>

    @Before
    fun setUp()
    {
        viewModel = FavoriteTvshowViewModel(movieRepository)
    }

    @Test
    fun getTvshowBookmarks() {
        val dummyTvshows = pagedList
        `when`(dummyTvshows.size).thenReturn(10)
        val tvshows = MutableLiveData<PagedList<TvshowEntity>>()
        tvshows.value = dummyTvshows

        `when`(movieRepository.getBookmarkedTvshows()).thenReturn(tvshows)
        val tvshowEntities = viewModel.getTvshowBookmarks().value
        verify(movieRepository).getBookmarkedTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities?.size)

        viewModel.getTvshowBookmarks().observeForever(observer)
        verify(observer).onChanged(dummyTvshows)
    }
}