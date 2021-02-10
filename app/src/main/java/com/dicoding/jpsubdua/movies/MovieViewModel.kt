package com.dicoding.jpsubdua.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jpsubdua.data.MovieEntity
import com.dicoding.jpsubdua.data.source.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies() : LiveData<List<MovieEntity>> = movieRepository.getAllMovies()
}

