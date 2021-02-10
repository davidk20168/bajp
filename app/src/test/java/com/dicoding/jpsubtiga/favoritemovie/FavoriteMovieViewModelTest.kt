package com.dicoding.jpsubtiga.favoritemovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
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
class FavoriteMovieViewModelTest {
    private lateinit var viewModel : FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp()
    {
        viewModel = FavoriteMovieViewModel(movieRepository)
    }

    @Test
    fun getMovieBookmarks() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieRepository.getBookmarkedMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovieBookmarks().value
        verify(movieRepository).getBookmarkedMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovieBookmarks().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}