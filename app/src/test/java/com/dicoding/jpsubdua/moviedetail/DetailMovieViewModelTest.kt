package com.dicoding.jpsubdua.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jpsubdua.data.MovieEntity
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
class DetailMovieViewModelTest {
    private lateinit var viewModel : DetailMovieViewModel
    private val dummyMovie = DataDummy.generateMovies()[0]
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var moviesObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(movieRepository.getContentMovie(dummyMovie.movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(movieRepository).getContentMovie(dummyMovie.movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.category, movieEntity.category)
        assertEquals(dummyMovie.synopsis, movieEntity.synopsis)
        assertEquals(dummyMovie.imagePoster, movieEntity.imagePoster)

        viewModel.getMovie().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovie)
    }
}