package com.dicoding.jpsubdua.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jpsubdua.data.MovieEntity
import com.dicoding.jpsubdua.data.source.MovieRepository

class DetailMovieViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var movieId : String

    fun setSelectedMovie(movieId : String)
    {
        this.movieId = movieId
    }

    fun getMovie() : LiveData<MovieEntity> {
        return movieRepository.getContentMovie(movieId)
    }
}