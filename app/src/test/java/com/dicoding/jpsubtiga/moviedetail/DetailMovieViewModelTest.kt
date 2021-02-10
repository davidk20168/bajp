package com.dicoding.jpsubtiga.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository
import com.dicoding.jpsubtiga.utils.DataDummy
import com.dicoding.jpsubtiga.vo.Resource
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
    private var dummyMovie = DataDummy.generateMovies()[0]
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(DataDummy.generateMovies()[0])
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(movieRepository.getContentMovie(dummyMovie.data?.movieId.toString())).thenReturn(movie)

        viewModel.getMovie().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovie)
    }
}