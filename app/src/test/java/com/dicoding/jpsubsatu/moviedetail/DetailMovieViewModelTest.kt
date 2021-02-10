package com.dicoding.jpsubsatu.moviedetail

import com.dicoding.jpsubsatu.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailMovieViewModelTest {
    private lateinit var viewModel : DetailMovieViewModel
    private val dummyMovie = DataDummy.generateMovies()[0]
    private val movieId = dummyMovie.movieId

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.category, movieEntity.category)
        assertEquals(dummyMovie.synopsis, movieEntity.synopsis)
        assertEquals(dummyMovie.imagePoster, movieEntity.imagePoster)
    }
}